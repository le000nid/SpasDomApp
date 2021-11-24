from API import ApiConnector
server = ApiConnector("http://51.250.24.236")

houses = server.get_houses()
HOUSES_AVAILABLE = [(elem['id'], f"{elem['street']} {elem['houseNumber']}") for elem in houses]
HOUSES_AVAILABLE_VAL = [(str(elem['id']), str(elem['id'])) for elem in houses]
ID_TO_STREET = {}
for elem in houses:
    ID_TO_STREET[elem['id']] = f"{elem['street']} {elem['houseNumber']}"

HOUSES_ASSIGNED = []
