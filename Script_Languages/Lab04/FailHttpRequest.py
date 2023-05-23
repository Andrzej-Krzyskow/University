from dataclasses import dataclass, field

from HttpRequest import HttpRequest


@dataclass
class FailHttpRequest(HttpRequest):
    __success: bool = field(default=False, init=False)

    @property
    def is_successful(self):
        return self.__success

    @property
    def is_failed(self):
        return self.__success

    def __bool__(self):
        return self.request_method and self.request_source and self.is_successful
