import logging
from Functions.parse_args import parse_args
from Functions.read_config import read_config
from Input.read_log import read_log_file
from Output.print_bytes_sum import print_bytes_sum
from Output.print_from_network import print_from_network


def test_read_config():
    config_obj = read_config("lab.config")
    assert config_obj.log_file == "access_log-20201025.txt"
    assert config_obj.config == {"debug": logging.INFO}
    assert config_obj.display == {"lines": 6,
                                  "separator": '|',
                                  "filter": "get"
                                  }


def test_parsing_args(ip_address, id_number):
    assert ip_address == "193.27.228.27"
    assert id_number == "266614"
    