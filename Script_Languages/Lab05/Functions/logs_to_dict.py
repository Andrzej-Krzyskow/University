from typing import Dict, List

from Classes import LogEntry
from Classes.AccessLog import AccessLog


def logs_to_dict(logs: List[LogEntry]):
    dictionary = AccessLog()
    for log in logs:
        if log.host in dictionary:
            dictionary[log.host].append(log)
        else:
            dictionary[log.host] = [log]

    return dictionary
