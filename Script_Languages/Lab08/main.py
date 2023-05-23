import argparse

from Functions.parse_args import parse_args
from Functions.read_config import read_config
from Input.read_log import read_log_file
from Output.print_bytes_sum import print_bytes_sum
from Output.print_from_network import print_from_network


def run():
    config_obj = read_config("lab.config")
    # print(config_data_obj)
    list_of_logs = read_log_file(config_obj.log_file, config_obj.config)
    # print(list_of_logs)

    args = parse_args()
    print_from_network(list_of_logs, args.ip_address, args.id_number, config_obj.display)
    print_bytes_sum(list_of_logs, config_obj)



if __name__ == '__main__':
    run()

