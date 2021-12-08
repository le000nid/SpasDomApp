from flask_wtf import FlaskForm
from wtforms import SubmitField, StringField, IntegerField
from wtforms.validators import DataRequired


class HouseForm(FlaskForm):
    city = StringField('Город', validators=[DataRequired()])
    area = StringField('Район', validators=[DataRequired()])
    street = StringField('Улица', validators=[DataRequired()])
    houseNumber = IntegerField('Номер дома', validators=[DataRequired()])
    submit = SubmitField('Отправить')


