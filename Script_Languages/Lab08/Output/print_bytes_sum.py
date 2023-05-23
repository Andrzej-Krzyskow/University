def print_bytes_sum(list_of_logs, config):
    sum = 0

    for log in list_of_logs:
        if log.http_request.request_method.lower() == "get":
            sum += log.data_amount

    #print(config)
    print(config.display["filter"], sum, sep=config.display["separator"])
