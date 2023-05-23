def pytest_addoption(parser):
    parser.addoption("--ip_address", '--ip', action="store", default="127.0.0.1")
    parser.addoption("--id_number", '--id', action="store", default="111111")


def pytest_generate_tests(metafunc):
    option_value_ip_address = metafunc.config.option.ip_address
    option_value_id_number = metafunc.config.option.id_number

    if "ip_address" in metafunc.fixturenames and option_value_ip_address is not None:
        metafunc.parametrize("ip_address", [option_value_ip_address])
    if "id_number" in metafunc.fixturenames and option_value_id_number is not None:
        metafunc.parametrize("id_number", [option_value_id_number])
