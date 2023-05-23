import json
import logging
import os.path
from json import JSONDecodeError

from Exceptions.InvalidHttpMethodError import InvalidHttpMethodError
from Exceptions.InvalidLoggingLevelError import InvalidLoggingLevelError
from Exceptions.LogFileNotFoundError import LogFileNotFoundError
from Exceptions.MissingParametersError import MissingParametersError


def read_json(json_file_name):

    try:
        required_keys = ["file_name",
                         "http_request_method",
                         "logging_level",
                         "log_lines_at_once",
                         "sort_by_size",
                         "data_less_than"]

        with open(json_file_name, "r") as f:
            config = json.load(f)

        if not all(k in config for k in required_keys):
            raise MissingParametersError

        if not os.path.exists(config["file_name"]):
            raise LogFileNotFoundError

        if config["http_request_method"].lower() not in ("get", "options", "head", "post", "put", "patch", "delete"):
            raise InvalidHttpMethodError

        if config["logging_level"].lower() not in ("debug", "info", "warning", "error", "critical"):
            raise InvalidLoggingLevelError

        match config["logging_level"]:
            case "debug":
                config["logging_level"] = logging.DEBUG
            case "info":
                config["logging_level"] = logging.INFO
            case "warning":
                config["logging_level"] = logging.WARNING
            case "error":
                config["logging_level"] = logging.ERROR
            case "critical":
                config["logging_level"] = logging.CRITICAL


        if not isinstance(config["log_lines_at_once"], int) or config["log_lines_at_once"] <= 0:
            raise ValueError

        if str(config["sort_by_size"]).lower() not in ("true", "false"):
            raise ValueError

        if not isinstance(config["data_less_than"], int):
            raise ValueError

    except FileNotFoundError:
        print("JSON file not found.")
    except LogFileNotFoundError:
        print("Log file not found.")
    except JSONDecodeError:
        print("Corrupted json file.")
    except MissingParametersError:
        print("Missing parameters in json file.")
    except InvalidLoggingLevelError:
        print("Invalid logging level.")
    except ValueError:
        print("Displayed lines at once must be positive.")

    else:
        print("File read correctly.")
        return config
    finally:
        print("Reading JSON done.")
