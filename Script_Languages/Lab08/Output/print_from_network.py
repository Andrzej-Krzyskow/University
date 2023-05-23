import ipaddress


def print_from_network(list_of_logs, ip_address, id_number, config):
    ip_mask = 24
    network_addr = ""

    try:
        ip_mask = int(id_number) % 16 + 8
    except ValueError:
        print("Incorrect id number, default used (/24)")

    try:
        network_addr = ipaddress.ip_network(ip_address + '/' + str(ip_mask), strict=False)
    except ValueError:
        print("Incorrect host address")

    display_counter = 0

    for log in list_of_logs:
        if display_counter >= config["lines"]:
            input("Press Enter to continue...")
            display_counter = 0

        if log.host in network_addr:
            display_counter += 1
            print(log)
