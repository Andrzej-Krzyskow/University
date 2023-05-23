from dataclasses import dataclass


@dataclass
class HttpRequest:
    request_method: str
    request_source: str

    def get_method(self):
        return self.request_method

    def get_source(self):
        return self.request_source

    def __str__(self):
        return f"{self.request_method} {self.request_source}"

    def __bool__(self):
        if self.request_method and self.request_source:
            return True
        return False

    def __lt__(self, other):
        if isinstance(other, HttpRequest):
            return len(self) < len(other)
        return False

    def __gt__(self, other):
        if isinstance(other, HttpRequest):
            return len(self) > len(other)
        return False

    def __len__(self):
        return len(self.request_method+self.request_source)
