from Classes.HostnameData import Hostname


def ip_requests(dict_of_logs, hostname: str) -> 'Hostname':
    return Hostname(len(dict_of_logs[hostname]))
