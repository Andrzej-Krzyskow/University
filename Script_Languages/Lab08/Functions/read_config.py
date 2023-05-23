import logging
import os.path
import re

from Classes.ConfigData import ConfigData


def read_config(config_file_name):
    __pattern__ = "\\[(\\w+)\\]\n((?:\\w+=.+\n?)*)"

    if not os.path.exists(config_file_name):
        print("File not found")
        exit(1)

    config = {}

    with open(config_file_name, "r") as f:
        matches = re.findall(__pattern__, f.read())
        for tuples in matches:
            head = tuples[0].lower()
            params = {}
            for x in tuples[1].split('\n'):
                if x.strip():
                    param_value = x.split('=')
                    params[param_value[0]] = param_value[1].lower()

            config[head] = params

    default_head_values = {

        "logfile": {
            "name": "untitled"
        },

        "config": {
            "debug": "debug"
        },

        "display": {
            "lines": 5,
            "separator": ';',
            "filter": "get"
        }
    }

    for defaults in default_head_values.items():
        if not defaults[0] in config:
            config[defaults[0]] = defaults[1]
            continue

        for def_param in defaults[1].items():
            if not def_param[0] in config[defaults[0]]:
                config[defaults[0]][def_param[0]] = def_param[1]

    # if not config["logfile"] or "name" not in config["logfile"]:
    #     config["logfile"]["name"] = "untitled"
    #
    # if not config["config"] or "debug" not in config["config"]:
    #     config["config"]["debug"] = "DEBUG"
    #
    # if not config["display"] or "lines" not in config["display"]:
    #     config["display"]["lines"] = 5
    #
    # if not config["display"] or "separator" not in config["display"]:
    #     config["display"]["separator"] = ';'
    #
    # if not config["display"] or "filter" not in config["display"]:
    #     config["display"]["filter"] = "get"

    # print(config)

    if not config["config"]["debug"] in ("debug", "info", "warning", "error", "critical"):
        print("Incorrect debug level")
        exit(1)

    match config["config"]["debug"]:
        case "debug":
            config["config"]["debug"] = logging.DEBUG
        case "info":
            config["config"]["debug"] = logging.INFO
        case "warning":
            config["config"]["debug"] = logging.WARNING
        case "error":
            config["config"]["debug"] = logging.ERROR
        case "critical":
            config["config"]["debug"] = logging.CRITICAL

    try:
        config["display"]["lines"] = int(config["display"]["lines"])
    except ValueError:
        print("Incorrect lines number")
        exit(1)

    return ConfigData(config["logfile"]["name"], config["config"], config["display"])
