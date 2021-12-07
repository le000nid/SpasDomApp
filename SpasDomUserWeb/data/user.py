import sqlalchemy
from .db_session import SqlAlchemyBase
from flask_login import UserMixin


class User(SqlAlchemyBase, UserMixin):
    __tablename__ = 'user'

    id = sqlalchemy.Column(sqlalchemy.Integer,
                           primary_key=True, autoincrement=True)
    username = sqlalchemy.Column(sqlalchemy.String, nullable=False)

    access_token = sqlalchemy.Column(sqlalchemy.String, nullable=False)
    access_expiry = sqlalchemy.Column(sqlalchemy.REAL, nullable=False)

    refresh_token = sqlalchemy.Column(sqlalchemy.String, nullable=False)
    refresh_expiry = sqlalchemy.Column(sqlalchemy.REAL, nullable=False)
