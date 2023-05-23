import sys

from CorrectHttpRequest import CorrectHttpRequest
from FailHttpRequest import FailHttpRequest
from HttpRequest import HttpRequest
from LogEntry import LogEntry
from file_to_log_entries import file_to_log_entries
from read_log import read_log
from create_datatime_obj import create_datetime_obj
from requests_between_dates import requests_between_dates


def test(file_name):

    list_of_logs1 = read_log(file_name)
    list_of_datetime_obj = [create_datetime_obj(x[1]) for x in list_of_logs1]

    req1 = HttpRequest(list_of_logs1[0][2], list_of_logs1[0][3])
    req2 = HttpRequest(list_of_logs1[1][2], list_of_logs1[1][3])
    print(req1)
    print(req2)
    print(list_of_datetime_obj[0])
    print(list_of_datetime_obj[1])

    log1 = LogEntry(list_of_logs1[0][0], list_of_datetime_obj[0], req1, list_of_logs1[0][4], list_of_logs1[0][5])
    log2 = LogEntry(list_of_logs1[1][0], list_of_datetime_obj[1], req2, list_of_logs1[1][4], list_of_logs1[1][5])
    print(log1)
    print(log2)

    reqC = CorrectHttpRequest(list_of_logs1[0][2], list_of_logs1[0][3])
    reqF = FailHttpRequest(list_of_logs1[1][2], list_of_logs1[1][3])
    print(reqC)
    print(reqF)
    print(reqC.is_successful)
    print(reqF.is_successful)

    t1 = HttpRequest(list_of_logs1[0][2], list_of_logs1[0][2])
    # t1 = req1
    if t1:
        print('WORKS', t1)
    else:
        print('WHAT?')

    print(log1 < log2)
    print(log1 == log2)
    print(req1 < req2)
    print(req1 == req2)

    # ??????????? how to change private
    print(reqC)
    reqC.success = False
    print(reqC)
    # ???????????


def run(file_name):

    # test(file_name)

    list_of_logs0 = file_to_log_entries(file_name)
    date1 = create_datetime_obj('01/Jul/1995:00:10:19 -0400')
    date2 = create_datetime_obj('01/Jul/1995:00:11:10 -0400')

    #requests_between_dates(date1, date2, list_of_logs0)

    # print(date2>date1)
    list_of_logs1 = read_log(file_name)
    list_of_datetime_obj = [create_datetime_obj(x[1]) for x in list_of_logs1]

    req1 = HttpRequest(list_of_logs1[0][2], list_of_logs1[0][3])
    req2 = HttpRequest(list_of_logs1[1][2], list_of_logs1[1][3])
    reqC = CorrectHttpRequest(list_of_logs1[0][2], list_of_logs1[0][3])
    print(repr(reqC))
    log1 = LogEntry(list_of_logs1[0][0], list_of_datetime_obj[0], req1, list_of_logs1[0][4], list_of_logs1[0][5])
    print(log1)


if __name__ == '__main__':
    if len(sys.argv) == 1:
        run('nasash')
    else:
        run(sys.argv[1])
else:
    exit()
