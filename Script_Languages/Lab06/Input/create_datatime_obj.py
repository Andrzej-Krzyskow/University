from datetime import datetime


def create_datetime_obj(timestamp):
    obj = datetime.strptime(timestamp, '%d/%b/%Y:%H:%M:%S %z')
    return obj
