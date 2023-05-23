from Input.process_line import line_to_log_entry


def read_log(file_name):
    file = open(file_name)
    list_of_logs = []

    for x in file:
        entry = line_to_log_entry(x)
        if entry:
            list_of_logs.append(entry)

    file.close()
    return list_of_logs
