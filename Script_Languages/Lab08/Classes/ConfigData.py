from dataclasses import dataclass


@dataclass
class ConfigData:
    log_file: str
    config: dict
    display: dict
