from Input.process_line import line_to_log_entry
import logging


def read_log_file(file_name, debug_lvl):
    logging.basicConfig(filename="app.log", encoding="UTF-8", level=debug_lvl, filemode="w")
    counter_read = 0
    file = open(file_name)
    list_of_logs = []

    for x in file:
        counter_read += 1
        entry = line_to_log_entry(x)
        if entry:
            list_of_logs.append(entry)

    file.close()

    logging.debug(f"Number of lines read: {counter_read}")
    logging.debug(f"Number of entries in the list: {len(list_of_logs)}")
    logging.info(f"File read successfully")
    if counter_read == 0:
        logging.warning(f"File empty.")

    return list_of_logs
