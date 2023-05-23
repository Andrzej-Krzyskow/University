import json


def create_json():
    config = {"file_name": input("Enter the name of the file with webserver logs: "),
              "http_request_method": input("Enter the name of an HTTP request method to use as a filter: "),
              "logging_level": input("Enter the logging level used by the application: ")}

    while True:
        try:
            config["log_lines_at_once"] = int(input("Enter the number of log lines to be displayed at once: "))
            if config["log_lines_at_once"] <= 0:
                print("Please enter positive integer number.")
                continue
            break
        except ValueError:
            print("Please enter a valid integer.")

    while True:
        user_input = input("Should the results be sorted: ")

        if user_input.lower() == "true":
            config["sort"] = True
        elif user_input.lower() == "false":
            config["sort"] = False
        else:
            print("Please enter \"True\" or \"False\".")
            continue
        break

    while True:
        try:
            config["data_less_than"] = int(input("Enter the limit for data size: "))
            if config["data_less_than"] <= 0:
                print("Please enter positive integer number.")
                continue
            break
        except ValueError:
            print("Please enter a valid integer.")

    with open("config.json", "w", encoding="utf-8") as f:
        json.dump(config, f, ensure_ascii=False, indent=4)
