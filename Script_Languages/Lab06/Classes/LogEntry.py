from dataclasses import dataclass
from datetime import datetime

from Classes import HttpRequest


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
        if (
                self.host is not None and
                self.date is not None and isinstance(self.date, datetime) and
                self.http_request is not None and
                self.response is not None and
                self.data_amount is not None
        ):
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

