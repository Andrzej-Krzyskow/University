from typing import List

from Classes.LogEntry import LogEntry


def get_from_host(list_of_logs: List[LogEntry], config):
    host = input("Provide host to search: ")
    logs = []

    display_counter = 0

    for x in list_of_logs:
        if x.host.lower() == host.lower() and x.data_amount <= config["data_less_than"]:
            logs.append(x)

    if config["sort_by_size"]:
        logs.sort(key=lambda x: x.data_amount, reverse=True)

    for x in logs:
        if display_counter >= config["log_lines_at_once"]:
            input("Press Enter to continue...")
            display_counter = 0
        display_counter += 1
        print(x)
