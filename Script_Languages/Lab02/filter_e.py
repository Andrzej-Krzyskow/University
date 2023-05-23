import sys
import re
from printing_output import printData


def filter_e(file_name):

    def process_line(line_to_process):
        line_pattern = "(\S+)(?: - - )\[(\d{2}/\w{3}/\d{4}:\d{2}:\d{2}:\d{2} [-]\d{4})\] (\"(?:[A-Z]+)\s+(\S+)(?: ?.+)?\") (\d+) (\d+|.*)"
        match = re.search(line_pattern, line_to_process)
        if match is not None and match[5] == "200":
            printData(match[0])

    if file_name:
        file = open(file_name, "r")
        for line in file:
            process_line(line)
        file.close()
    else:
        from sys import stdin
        for line in stdin:
            process_line(line)


if __name__ == '__main__':
    if len(sys.argv) == 1:
        filter_e("")
    else:
        filter_e(sys.argv[1])
