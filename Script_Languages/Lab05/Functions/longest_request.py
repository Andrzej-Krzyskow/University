from typing import Dict, List

from Classes import LogEntry



def longest_request(dict_of_logs: Dict[str, List[LogEntry]]):
    request_line = ''
    hostname = ''

    for host, logs in dict_of_logs.items():
        for x in logs:
            if len(x.http_request) > len(request_line):
                request_line = x.http_request
                hostname = host
                break

    return hostname, request_line
