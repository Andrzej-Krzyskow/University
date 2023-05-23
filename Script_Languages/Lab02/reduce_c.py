import sys
import re
from printing_output import printData


def reduce_c(file_name):
    max_data_size = 0
    file_path_of_max = ""

    def process_line(line_to_process):
        line_pattern = "(\S+)(?: - - )\[(\d{2}/\w{3}/\d{4}:\d{2}:\d{2}:\d{2} [-]\d{4})\] (\"(?:[A-Z]+)\s+(\S+)(?: ?.+)?\") (\d+) (\d+|.*)"
        match = re.search(line_pattern, line_to_process)
        if match is not None:
            try:
                nonlocal max_data_size, file_path_of_max
                if int(match[6]) > max_data_size:
                    max_data_size = int(match[6])
                    file_path_of_max = match[4]
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

    printData(str(max_data_size) + " " + file_path_of_max)


if __name__ == '__main__':
    if len(sys.argv) == 1:
        reduce_c("")
    else:
        reduce_c(sys.argv[1])
