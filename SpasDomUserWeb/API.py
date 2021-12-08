import requests


class ApiConnector:
    def __init__(self, api_url):
        self.api_url = api_url
        self.announcements = '/announcements'
        self.houses = '/houses'
        self.login = '/auth/admin/login'
        self.register = '/auth/admin/register'
        self.apartments = '/apartments'
        self.access_header = None

    def update_token(self, new_token):
        self.access_header = {'Authorization': f'Bearer {new_token}'}

    def post_announcement(self, body, post_date, death_date, category, houses_assigned, title=""):
        data = {
            "title": title,
            "body": body,
            "postDate": post_date,
            "deathDate": death_date,
            "category": int(category),
            "houseIds": [
                int(el) for el in houses_assigned
            ]
        }
        print(data)
        request = requests.post(self.api_url + self.announcements, json=data, headers=self.access_header)
        return request.status_code

    def get_announcements(self):
        response = requests.get(self.api_url + self.announcements, headers=self.access_header)
        return response.json()

    def del_announcement(self, a_id):
        response = requests.delete(self.api_url + self.announcements + f'/{a_id}', headers=self.access_header)
        return response.status_code

    def get_announcement(self, a_id):
        pass

    def get_houses(self):
        response = requests.get(self.api_url + self.houses, headers=self.access_header)
        return response.json()

    def post_house(self, city, street, area, number):
        data = {
            'city': city,
            'street': street,
            'area': area,
            'houseNumber': number
        }
        response = requests.post(self.api_url + self.houses, json=data, headers=self.access_header)
        return response.status_code

    def auth_login(self, username, password):
        response = requests.post(self.api_url + self.login,
                                 json={
                                     "login": username,
                                     "password": password
                                 })
        if response.status_code != 200:  # unauthorised
            return None
        return response.json()

    def auth_register(self, username, password):
        # temporary for register check
        from data import db_session
        from data.user import User
        from datetime import datetime

        response = requests.post(self.api_url + self.register,
                                 json={
                                     "login": username,
                                     "password": password
                                 })
        if response.status_code != 200:  # login already exist
            return None
        tokens = response.json()

        session = db_session.create_session()
        access_expiry = (datetime.strptime(tokens["access"]["expiry"][:26], '%Y-%m-%dT%H:%M:%S.%f') -
                         datetime(2000, 1, 1)).total_seconds()
        refresh_expiry = (datetime.strptime(tokens["refresh"]["expiry"][:26], '%Y-%m-%dT%H:%M:%S.%f') -
                          datetime(2000, 1, 1)).total_seconds()
        new_user = User(
            username=username,
            access_token=tokens["access"]["token"],
            access_expiry=access_expiry,
            refresh_token=tokens["refresh"]["token"],
            refresh_expiry=refresh_expiry,
        )
        session.add(new_user)
        session.commit()
        return response.status_code

    def get_apartments(self, house_id):
        response = requests.get(self.api_url + self.apartments,
                                json={
                                    'houseId': house_id
                                })
        if response.status_code != 200:
            return None

        return response.json()
