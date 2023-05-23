import sys
import re
from printing_output import printData


def reduce_b(file_name):
    data_size = 0

    def process_line(line_to_process):
        line_pattern ="(\S+)(?: - - )\[(\d{2}/\w{3}/\d{4}:\d{2}:\d{2}:\d{2} [-]\d{4})\] (\"(?:[A-Z]+)\s+(\S+)(?: ?.+)?\") (\d+) (\d+|.*)"
        match = re.search(line_pattern, line_to_process)
        if match is not None and match[5] != "404":
            try:
                nonlocal data_size
                data_size += int(match[6])
            except:
                return

    if file_name:
        file = open(file_name, "r")
        for line in file:
            process_line(line.strip())
        file.close()
    else:
        from sys import stdin
        for line in stdin:
            process_line(line)

    printData(round(data_size / (1024 ** 3),2))

if __name__ == '__main__':
    if len(sys.argv) == 1:
        reduce_b("")
    else:
        reduce_b(sys.argv[1])
