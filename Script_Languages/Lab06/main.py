from Functions.createJSON import create_json
from Functions.get_from_host import get_from_host
from Functions.get_valid_from_resource import get_valid_from_resource
from Functions.read_json import read_json
from Input.read_log import read_log_file


def run():

    if input("Do you want to create json [y/n]:").lower() == 'y':
        create_json()

    config = read_json("config.json")

    if config:
        list_of_logs = read_log_file(config["file_name"], config["logging_level"])
        get_valid_from_resource(list_of_logs, config)
        get_from_host(list_of_logs, config)


if __name__ == '__main__':
    run()
