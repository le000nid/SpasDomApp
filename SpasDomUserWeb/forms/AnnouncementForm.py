from flask_wtf import FlaskForm
from wtforms import SubmitField, SelectField, SelectMultipleField, StringField
from wtforms.validators import DataRequired
from wtforms.fields.html5 import DateField, TimeField
from wtforms.widgets import TextArea
from constans import HOUSES_ASSIGNED, HOUSES_AVAILABLE


class AnnouncementForm(FlaskForm):
    announcement_category = SelectField('Тип оповещения', choices=[(0, 'Тип 1'), (1, 'Тип 2')])
    title = StringField('Заголовок', validators=[DataRequired()])

    # date + time to POST to App
    post_date = DateField('Время и дата появления', validators=[DataRequired()])
    post_time = TimeField(validators=[DataRequired()])

    # data + time to DEL from App
    death_date = DateField('Время и дата смерти', validators=[DataRequired()])
    death_time = TimeField(validators=[DataRequired()])

    # announcements will be used to houses_assigned
    houses_available = SelectMultipleField('Выбрать дома', choices=HOUSES_AVAILABLE)
    houses_assigned = SelectMultipleField('Выбранные дома', choices=HOUSES_ASSIGNED)

    # description
    body = StringField('Описание', widget=TextArea())

    submit = SubmitField('Отправить')


