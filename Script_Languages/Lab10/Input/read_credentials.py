import json


def read_credentials(filename):
    data = {}

    with open(filename) as f:
        data = json.load(f)

    return data
