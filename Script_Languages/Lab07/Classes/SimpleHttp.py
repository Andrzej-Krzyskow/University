from dataclasses import dataclass


@dataclass
class SimpleHttp:
    method: str
    path: str
    protocol: str
