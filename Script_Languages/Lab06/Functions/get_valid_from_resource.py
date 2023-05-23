from typing import List

from Classes.LogEntry import LogEntry


def get_valid_from_resource(list_of_logs: List[LogEntry], config):
    resource = input("Provide resource to search: ")
    invalid_requests = []
    display_counter = 0

    for x in list_of_logs:
        if display_counter >= config["log_lines_at_once"]:
            input("Press Enter to continue...")
            display_counter = 0
        if (x.http_request.request_method.lower() == config["http_request_method"].lower()
                and x.http_request.request_source.lower() == resource.lower()):
            match x.response:
                case res if 200 <= res < 300:
                    display_counter += 1
                    print(x)
                case res if 300 <= res < 600:
                    invalid_requests.append(x)

    print(f"\nError or redirect requests:\n", invalid_requests)
