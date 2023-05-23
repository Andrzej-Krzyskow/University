from datetime import datetime


def requests_between_dates(date1, date2, list_of_log_entries):
    if not (isinstance(date1, datetime) and isinstance(date2, datetime) and date1 < date2):
        raise AttributeError

    for x in list_of_log_entries:
        if date1 <= x.date <= date2:
            print(x)
