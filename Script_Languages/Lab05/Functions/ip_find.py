from typing import Dict, List

from Classes import LogEntry


def ip_find(dict_of_logs: Dict[str, List[LogEntry]], most_active=True):
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
