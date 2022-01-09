from flask_wtf import FlaskForm
from wtforms import SubmitField, SelectMultipleField, BooleanField


class ApartmentsChooseForm(FlaskForm):
    apartments_available = SelectMultipleField('Доступные квартиры', choices=[])
    apartments_assigned = SelectMultipleField('Выбранные квартиры', choices=[])

    select_all = BooleanField('Выбрать все')
    submit = SubmitField('Подтвердить')
