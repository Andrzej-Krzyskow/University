import sys
import re
from printing_output import printData


def reduce_d(file_name):
    counter_all = 0
    counter_images = 0

    def process_line(line_to_process):
        line_pattern = "(\S+)(?: - - )\[(\d{2}/\w{3}/\d{4}:\d{2}:\d{2}:\d{2} [-]\d{4})\] (\"(?:[A-Z]+)\s+(\S+)(?: ?.+)?\") (\d+) (\d+|.*)"
        match = re.search(line_pattern, line_to_process)
        if match is not None:
            nonlocal counter_all, counter_images
            image_pattern = ".gif|.jpg|.jpeg|.xbm"
            image_match = re.search(image_pattern, match[4])

            if image_match is not None:
                counter_images += 1
            counter_all += 1


    if file_name:
        file = open(file_name, "r")
        for line in file:
            process_line(line.strip())
        file.close()
    else:
        from sys import stdin
        for line in stdin:
            process_line(line)

    if counter_all != 0:
        printData(round(counter_images / counter_all, 2))
    else:
        printData(0)


if __name__ == '__main__':
    if len(sys.argv) == 1:
        reduce_d("")
    else:
        reduce_d(sys.argv[1])
