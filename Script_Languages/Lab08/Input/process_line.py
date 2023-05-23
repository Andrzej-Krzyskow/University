import ipaddress

from Classes.HttpRequest import HttpRequest
from Classes.LogEntry import LogEntry
import re

from Input.create_datatime_obj import create_datetime_obj
from Input.pattern import __pattern_to_compile__


def line_to_log_entry(line):
    line_pattern = __pattern_to_compile__
    match = re.search(line_pattern, line)
    if match:
        request = HttpRequest(str(match[3].split()[0][1:]), match[4])
        date = create_datetime_obj(match[2])
        address = ipaddress.ip_address(match[1])
        try:
            log = LogEntry(address, date, request, int(match[5]), int(match[6]))
            return log
        except ValueError:
            try:
                return LogEntry(address, date, request, int(match[5]), 0)
            except ValueError:
                pass

    else:
        return
