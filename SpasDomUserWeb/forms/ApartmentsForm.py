from flask_wtf import FlaskForm
from wtforms import SubmitField, SelectField, StringField
from wtforms.validators import DataRequired


class ApartmentsForm(FlaskForm):
    houseId = SelectField('Выберите дом', validators=[DataRequired()])
    businessAccount = StringField('businessAccount', validators=[DataRequired()])
    password = StringField('password', validators=[DataRequired()])
    submit = SubmitField('Подтвердить')
