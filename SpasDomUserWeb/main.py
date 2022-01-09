import json
from datetime import datetime
from flask import Flask, render_template, redirect, request, make_response
from flask_login import LoginManager, login_user, login_required, logout_user, current_user
from data import db_session
from forms.LoginForm import LoginForm
from forms.AnnouncementForm import AnnouncementForm
from forms.MarketplaceForm import MarketplaceForm
from forms.HouseForm import HouseForm
from forms.ApartmentsForm import ApartmentsForm
from forms.ApartmentsChooseForm import ApartmentsChooseForm
from data.user import User
from API import ApiConnector


server = ApiConnector("http://34.125.200.54")

HOUSES_AVAILABLE = []
HOUSES_AVAILABLE_VAL = []

app = Flask(__name__)
login_manager = LoginManager()
login_manager.init_app(app)
app.config['WTF_CSRF_ENABLED'] = True
app.config['SECRET_KEY'] = 'some_key_for_security'


def update_houses_var():
    global HOUSES_AVAILABLE, HOUSES_AVAILABLE_VAL
    new_houses = server.get_houses()
    HOUSES_AVAILABLE = [(elem['id'], f"{elem['street']} {elem['houseNumber']}") for elem in new_houses]
    HOUSES_AVAILABLE_VAL = [(str(elem['id']), str(elem['id'])) for elem in new_houses]


def update_server_token():
    session = db_session.create_session()
    user = session.query(User).filter(User.username == current_user.username).first()
    server.update_token(user.access_token)


@app.before_request
def check_tokens():
    if current_user.is_authenticated and request.endpoint != 'logout':
        # refresh logic
        # logout logic if refresh token is exp
        pass


# Все, что касается login
@login_manager.user_loader
def load_user(user_id):
    session = db_session.create_session()
    return session.query(User).get(user_id)


@app.route('/logout')
@login_required
def logout():
    logout_user()
    return redirect("/")


@app.route('/', methods=['GET', 'POST'])
def login():
    form = LoginForm()
    if request.method == 'POST':
        if form.validate_on_submit():
            tokens_data = server.auth_login(form.username.data, form.password.data)

            # wrong Password or Login
            if tokens_data is None:
                return render_template('LoginTemplate.html',
                                       message="Wrong login or password",
                                       form=form, title='Login')

            session = db_session.create_session()
            user_q = session.query(User).filter(User.username == form.username.data)

            access_expiry = (datetime.strptime(tokens_data["access"]["expiry"][:26], '%Y-%m-%dT%H:%M:%S.%f') -
                             datetime(2000, 1, 1)).total_seconds()
            refresh_expiry = (datetime.strptime(tokens_data["refresh"]["expiry"][:26], '%Y-%m-%dT%H:%M:%S.%f') -
                              datetime(2000, 1, 1)).total_seconds()

            if user_q.first():
                user_q.update({
                    "access_token":   tokens_data["access"]["token"],
                    "access_expiry":  access_expiry,
                    "refresh_token":  tokens_data["refresh"]["token"],
                    "refresh_expiry": refresh_expiry,
                })
                login_user(user_q.first(), remember=form.remember_me.data)
                session.commit()
            else:
                forgotten_user = User(
                    username=form.username.data,
                    access_token=tokens_data["access"]["token"],
                    access_expiry=access_expiry,
                    refresh_token=tokens_data["refresh"]["token"],
                    refresh_expiry=refresh_expiry,
                )
                session.add(forgotten_user)
                session.commit()
                login_user(forgotten_user, remember=form.remember_me.data)

            return redirect("/announcement")
    return render_template('LoginTemplate.html', form=form,
                           title='Login', message='')


@app.route('/announcement', methods=['GET', 'POST'])
@login_required
def announcements():
    apart_form = ApartmentsChooseForm()
    ann_form = AnnouncementForm()

    ann_form.houses_available.choices = HOUSES_AVAILABLE
    ann_form.houses_assigned.choices = []

    update_server_token()

    if request.method == 'POST':
        # to let ann_form validate properly
        ann_form.houses_assigned.choices = HOUSES_AVAILABLE_VAL
        if ann_form.validate_on_submit():
            status_code = server.post_announcement(
                category=ann_form.announcement_category.data,
                title=ann_form.title.data,
                body=ann_form.body.data,
                post_date=f'{ann_form.post_date.data}T{ann_form.post_time.data}Z',
                death_date=f'{ann_form.death_date.data}T{ann_form.death_time.data}Z',
                houses_assigned=ann_form.houses_assigned.data
            )
            if status_code != 200:
                pass
        ann_form.houses_assigned.choices = []

    announcements_list = server.get_announcements()
    return render_template('AnnouncementTemplate.html',
                           ann_form=ann_form, apart_form=apart_form,
                           title='Оповещения', announcements=announcements_list,
                           message='')


@app.route('/select_apart/<int:house_id>', methods=['POST'])
@login_required
def select_apart(house_id):
    apartments_list = server.get_apartments(house_id)
    apartments_choices = [(f'Квартира {i["id"]}', i["id"]) for i in apartments_list]
    response = make_response(json.dumps(apartments_choices))
    response.content_type = 'application/jsons'
    return response


@app.route('/del_announcement/<int:a_id>')
@login_required
def delete_announcement(a_id):
    update_server_token()
    server.del_announcement(a_id)
    return redirect('/announcement')


@app.route('/del_house/<int:h_id>')
@login_required
def delete_house(h_id):
    update_server_token()
    server.del_house(h_id)
    update_houses_var()
    return redirect('/add_house')


@app.route('/marketplace', methods=['GET', 'POST'])
@login_required
def marketplace():
    form = MarketplaceForm()
    if form.validate_on_submit():
        pass
    return render_template('MarketplaceTemplate.html', form=form,
                           title='Маркетплэйс', message='')


@app.route('/timetable', methods=['GET', 'POST'])
@login_required
def timetable():
    timetable_data = server.get_calendar()
    return render_template('TimetableTemplate.html', title='Расписание',
                           data=timetable_data)


@app.route('/add_house', methods=['GET', 'POST'])
@login_required
def add_house():
    form = HouseForm()
    if request.method == 'POST':
        if form.validate_on_submit():
            server.post_house(
                city=form.city.data,
                area=form.area.data,
                street=form.street.data,
                number=form.houseNumber.data
            )
            update_houses_var()
    houses_list = server.get_houses()
    return render_template('HouseTemplate.html', form=form,
                           title='Добавление домов', message='',
                           houses=houses_list)


@app.route('/add_apartments', methods=['GET', 'POST'])
@login_required
def add_apartments():
    form = ApartmentsForm()
    form.houseId.choices = HOUSES_AVAILABLE
    apartments_list = []
    for house in HOUSES_AVAILABLE:
        resp = server.get_apartments(int(house[0]))
        if resp:
            apartments_list += resp

    if request.method == 'POST':
        if form.validate_on_submit():
            server.post_apartments(
                house_id=form.houseId.data,
                business_a=form.businessAccount.data,
                password=form.password.data
            )
    return render_template('ApartmentsTemplate.html', form=form,
                           title='Добавление квартир', message='',
                           apartments_list=apartments_list)


if __name__ == '__main__':
    db_session.global_init("db/data.sqlite")
    update_houses_var()
    app.run(port=8080, host='127.0.0.1', debug=True)
