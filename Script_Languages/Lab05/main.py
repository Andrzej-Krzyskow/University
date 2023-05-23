import sys

from Functions.LogsManager import LogsManager
from Classes.AccessLog import AccessLog


def run(file_name):
    # list_of_logs = read_log(file_name)
    # dict_of_logs = logs_to_dict(list_of_logs)
    # print(list_of_logs)
    # print(dict_of_logs)
    # print(dict_of_logs.keys())
    # print(dict_of_logs['199.120.110.21'])

    dict_of_logs = AccessLog.get_access_log(file_name)

    # print('dict', dict_of_logs)
    # print('items', dict_of_logs.items())
    # print('keys', dict_of_logs.keys())
    # print('values', dict_of_logs.values())
    # for host in dict_of_logs:
    #    print(host, end=", ")
    # print('\n')

    print(LogsManager.ip_requests(dict_of_logs, 'burger.letters.com'))
    print(LogsManager.ip_find(dict_of_logs, most_active=True))
    print(LogsManager.longest_request(dict_of_logs))
    print(LogsManager.non_existent(dict_of_logs))


if __name__ == '__main__':
    if len(sys.argv) == 1:
        run('nasash')
    else:
        run(sys.argv[1])
else:
    exit()
