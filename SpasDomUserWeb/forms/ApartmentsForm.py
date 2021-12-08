from flask_wtf import FlaskForm
from wtforms import SubmitField, SelectMultipleField, BooleanField
from wtforms.validators import DataRequired


class ApartmentsForm(FlaskForm):
    apartments_available = SelectMultipleField('Доступные квартиры', choices=[])
    apartments_assigned = SelectMultipleField('Выбранные квартиры', choices=[])

    select_all = BooleanField('Выбрать все')
    submit = SubmitField('Подтвердить')
