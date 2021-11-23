import requests


class ApiConnector:
    def __init__(self, api_url):
        self.api_url = api_url
        self.announcements = '/announcements'
        self.houses = '/houses'

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
        request = requests.post(self.api_url + self.announcements, json=data)
        return request.status_code

    def get_announcements(self):
        response = requests.get(self.api_url + self.announcements)
        return response.json()

    def del_announcement(self, a_id):
        response = requests.delete(self.api_url + self.announcements + f'/{a_id}')
        return response.status_code

    def get_announcement(self, a_id):
        pass

    def get_houses(self):
        response = requests.get(self.api_url + self.houses)
        return response.json()
