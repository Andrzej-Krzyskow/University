from typing import Dict, List

from Classes import LogEntry
from Classes.HostnameData import Hostname


class LogsManager:

    @classmethod
    def ip_find(cls, dict_of_logs: Dict[str, List[LogEntry]], most_active=True):
        hostnames = []

        if most_active:
            counter = 0
        else:
            counter = float('inf')

        for host, logs in dict_of_logs.items():

            if (
                    (most_active and len(logs) > counter)
                    or (not most_active and len(logs) < counter)
            ):
                counter = len(logs)
                hostnames = [host]

            elif (
                    (most_active and len(logs) == counter)
                    or (not most_active and len(logs) == counter)
            ):
                hostnames.append(host)

        return hostnames

    @classmethod
    def ip_requests(cls, dict_of_logs, hostname: str) -> 'Hostname':
        return Hostname(len(dict_of_logs[hostname]))

    @classmethod
    def longest_request(cls, dict_of_logs: Dict[str, List[LogEntry]]):
        request_line = ''
        hostname = ''

        for host, logs in dict_of_logs.items():
            for x in logs:
                if len(x.http_request) > len(request_line):
                    request_line = x.http_request
                    hostname = host
                    break

        return hostname, request_line

    @classmethod
    def non_existent(cls, dict_of_logs: Dict[str, List[LogEntry]]):

        failed_requests = set()

        for host, logs in dict_of_logs.items():
            for x in logs:
                if x.response == 404:
                    failed_requests.add(x.http_request.request_source)

        return failed_requests
