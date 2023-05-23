from dataclasses import dataclass, field

from HttpRequest import HttpRequest


@dataclass
class CorrectHttpRequest(HttpRequest):
    success: bool = field(default=True, init=False)

    @property
    def is_successful(self):
        return self.success

    @property
    def is_failed(self):
        return self.success

    def __bool__(self):
        return self.request_method and self.request_source and self.is_successful
