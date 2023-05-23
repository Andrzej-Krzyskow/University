from pattern import __pattern_to_compile__
import re
import logging


def read_log(file_name):
    logging.basicConfig(filename='read_debug.log', level=logging.DEBUG, filemode='w')
    counter_read = 0

    file = open(file_name, 'r')
    list_of_logs = []
    line_pattern = __pattern_to_compile__

    for line in file:
        counter_read += 1
        match = re.search(line_pattern, line)
        if match is not None:
            try:
                list_of_logs.append((match[1], match[2],match[3].split()[0][1:], match[4], int(match[5]), int(match[6])))
            except ValueError:
                try:
                    list_of_logs.append((match[1], match[2], match[3].split()[0][1:], match[4], int(match[5]), 0))
                except ValueError:
                    continue

    file.close()

    logging.debug(f"Number of lines read: {counter_read}")
    logging.debug(f"Number of entries in the list: {len(list_of_logs)}")

    return list_of_logs
