import pytest as pytest

from Classes.SimpleHttp import SimpleHttp
from Exceptions.BadHttpVersionError import BadHttpVersionError
from Exceptions.BadRequestTypeError import BadRequestTypeError


def req_str2obj(request_string):
    if not isinstance(request_string, str):
        raise TypeError('request_string must be a string')

    fields = request_string.split()

    if len(fields) != 3:
        return None

    if fields[0].lower() not in ["get", "options", "head", "post", "put", "patch", "delete"]:
        raise BadRequestTypeError

    if not fields[1].lower().startswith('/'):
        raise ValueError("Path must start with /")

    if fields[2].lower() not in ["http1.0", "http1.1", "http2.0"]:
        raise BadHttpVersionError

    return SimpleHttp(fields[0], fields[1], fields[2])


def test_1():
    with pytest.raises(TypeError):
        req_str2obj(123)


def test_2():
    http_request = req_str2obj("GET / HTTP1.1")
    assert isinstance(http_request, SimpleHttp)


def test_3():
    http_request = req_str2obj("GET / HTTP1.1")
    assert isinstance(http_request, SimpleHttp)
    assert http_request.method == "GET"
    assert http_request.path == "/"
    assert http_request.protocol == "HTTP1.1"


def test_4():
    http_request = req_str2obj("POST /my_path/to/file HTTP1.0")
    assert isinstance(http_request, SimpleHttp)
    assert http_request.method == "POST"
    assert http_request.path == "/my_path/to/file"
    assert http_request.protocol == "HTTP1.0"


def test_5():
    http_request = req_str2obj("")
    assert http_request is None


def test_6():
    with pytest.raises(BadRequestTypeError):
        http_request = req_str2obj("DOWNLOAD /my/path HTTP1.0")


def test_7():
    with pytest.raises(BadHttpVersionError):
        http_request = req_str2obj("GET /my/path HTTP1.2")


def test_8():
    with pytest.raises(ValueError):
        http_request = req_str2obj("GET my/path HTTP1.1")
