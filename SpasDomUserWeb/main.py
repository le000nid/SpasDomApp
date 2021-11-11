from flask import Flask, render_template, redirect
from flask_login import LoginManager, login_user, login_required, logout_user, current_user
from data import db_session
from forms.LoginForm import LoginForm
from data.user import User


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


@app.route('/login', methods=['GET', 'POST'])
def login():
    form = LoginForm()
    if form.validate_on_submit():
        session = db_session.create_session()
        user = session.query(User).filter(User.username == form.username.data).first()
        if user and user.check_password(form.password.data):
            login_user(user, remember=form.remember_me.data)
            return redirect("/")
        return render_template('LoginTemplate.html',
                               message="Wrong login or password",
                               form=form, title='Login')
    return render_template('LoginTemplate.html', form=form,
                           title='Login', message='')


# Main window
@app.route('/', methods=['GET', 'POST'])
def main():
    return render_template('MainTemplate.html',
                           title='Main')


if __name__ == '__main__':
    db_session.global_init("db/data.sqlite")
    app.run(port=8080, host='127.0.0.1', debug=True)
