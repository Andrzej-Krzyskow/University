from dataclasses import dataclass
from datetime import datetime

from HttpRequest import HttpRequest


@dataclass
class LogEntry:
    host: str
    date: datetime
    http_request: HttpRequest
    response: int
    data_amount: int

    def __str__(self):
        return f"Host: {self.host}, Date: {self.date}, HTTP request: {self.http_request}, Response: {self.response}, " \
               f"Data amount: {self.data_amount}"

    def __bool__(self):
        if self.host and self.date and self.http_request and self.response and self.data_amount:
            return True
        return False

    def __lt__(self, other):
        if isinstance(other, LogEntry):
            return self.date < other.date
        return False

    def __gt__(self, other):
        if isinstance(other, LogEntry):
            return self.date > other.date
        return False
