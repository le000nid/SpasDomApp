from flask import Flask, render_template, redirect, request
from flask_login import LoginManager, login_user, login_required, logout_user
from data import db_session
from forms.LoginForm import LoginForm
from forms.AnnouncementForm import AnnouncementForm
from forms.MarketplaceForm import MarketplaceForm
from data.user import User
from constans import HOUSES_ASSIGNED, HOUSES_AVAILABLE_VAL
from API import ApiConnector


server = ApiConnector("http://51.250.24.236")

app = Flask(__name__)
login_manager = LoginManager()
login_manager.init_app(app)
app.config['SECRET_KEY'] = 'some_key_for_security'


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
    if form.validate_on_submit():
        session = db_session.create_session()
        user = session.query(User).filter(User.username == form.username.data).first()
        if user and user.check_password(form.password.data):
            login_user(user, remember=form.remember_me.data)
            return redirect("/announcement")
        return render_template('LoginTemplate.html',
                               message="Wrong login or password",
                               form=form, title='Login')
    return render_template('LoginTemplate.html', form=form,
                           title='Login', message='')


@app.route('/announcement', methods=['GET', 'POST'])
def alerts():
    form = AnnouncementForm()

    if request.method == 'POST':
        # to let form validate properly
        form.houses_assigned.choices = HOUSES_AVAILABLE_VAL
        if form.validate_on_submit():
            status_code = server.post_announcement(
                category=form.announcement_category.data,
                title=form.title.data,
                body=form.body.data,
                post_date=f'{form.post_date.data}T{form.post_time.data}Z',
                death_date=f'{form.death_date.data}T{form.death_time.data}Z',
                houses_assigned=form.houses_assigned.data
            )
            if status_code != 200:
                pass
        form.houses_assigned.choices = HOUSES_ASSIGNED

    announcements = server.get_announcements()
    return render_template('AnnouncementTemplate.html', form=form,
                           title='Оповещения', announcements=announcements,
                           message='')


@app.route('/announcement/<int:a_id>')
def delete_announcement(a_id):
    server.del_announcement(a_id)
    return redirect('/announcement')


@app.route('/marketplace', methods=['GET', 'POST'])
def marketplace():
    form = MarketplaceForm()
    if form.validate_on_submit():
        pass
    return render_template('MarketplaceTemplate.html', form=form,
                           title='Маркетплэйс', message='')


@app.route('/timetable', methods=['GET', 'POST'])
def timetable():
    return render_template('TimetableTemplate.html', title='Расписание')


if __name__ == '__main__':
    db_session.global_init("db/data.sqlite")
    app.run(port=8080, host='127.0.0.1', debug=True)
