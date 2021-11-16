from flask_wtf import FlaskForm
from wtforms import StringField, SubmitField, SelectField, TextAreaField
from wtforms.validators import DataRequired
from wtforms.widgets import  TextArea


class MarketplaceForm(FlaskForm):
    title = StringField('Название', validators=[DataRequired()])
    type = SelectField('Тип оповещения', choices=['Тип 1', 'Тип 2', 'Тип 3'], validators=[DataRequired()])
    subscription = StringField('Описание', widget=TextArea())
    master_name = SelectField('ФИО Мастера', choices=['Мастер 1', 'Мастер 2', 'Мастер 3'])
    master_id = SelectField('Id Мастера', choices=['ID-001', 'ID-002', 'ID-003'])
    submit = SubmitField('Отправить')
