from flask_wtf import FlaskForm
from wtforms import StringField, PasswordField, BooleanField, SubmitField, SelectField, SelectMultipleField
from wtforms.validators import DataRequired, Required
from wtforms.fields.html5 import DateField


class AlertsForm(FlaskForm):
    alert_type = SelectField('Тип оповещения', choices=['Тип 1', 'Тип 2', 'Тип 3'], validators=[DataRequired()])
    date = DateField('Дата', format='%d-%m-%y')
    houses = SelectMultipleField('Выбрать дома', choices=[(str(i).rjust(4, '0'), str(i).rjust(4, '0'))
                                                          for i in range(100)])
    submit = SubmitField('Отправить')


