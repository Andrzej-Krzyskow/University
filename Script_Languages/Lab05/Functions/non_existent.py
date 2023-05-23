from typing import Dict, List

from Classes import LogEntry


def non_existent(dict_of_logs: Dict[str, List[LogEntry]]):

    failed_requests = set()

    for host,logs in dict_of_logs.items():
        for x in logs:
            if x.response == 404:
                failed_requests.add(x.http_request.request_source)

    return failed_requests
