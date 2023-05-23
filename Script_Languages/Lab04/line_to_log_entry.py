from HttpRequest import HttpRequest
from LogEntry import LogEntry
import re

from create_datatime_obj import create_datetime_obj
from pattern import __pattern_to_compile__


def line_to_log_entry(line):
    line_pattern = __pattern_to_compile__
    match = re.search(line_pattern, line)
    if match is not None:
        request = HttpRequest(str(match[3].split()[0][1:]), match[4])
        date = create_datetime_obj(match[2])
        try:
            l = LogEntry(match[1], date, request, int(match[5]), int(match[6]))
            return l
        except ValueError:
            try:
                return LogEntry(match[1], date, request, int(match[5]), 0)
            except ValueError:
                pass

    else:
        return
