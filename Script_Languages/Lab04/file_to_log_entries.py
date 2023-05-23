from line_to_log_entry import line_to_log_entry


def file_to_log_entries(file_name):
    file = open(file_name)
    list_of_logs = []

    for x in file:
        entry = line_to_log_entry(x)
        if entry is not None:
            list_of_logs.append(entry)

    file.close()
    return list_of_logs
