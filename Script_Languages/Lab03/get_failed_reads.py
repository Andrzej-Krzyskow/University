import logging


def get_failed_reads(list_of_logs, return_one_list=True):
    logging.basicConfig(filename='read_debug.log', level=logging.INFO, filemode='a')

    new_list_4xx = [x for x in list_of_logs if 400 <= x[3] < 500]
    new_list_5xx = [x for x in list_of_logs if 500 <= x[3] < 600]

    logging.info(f"Failed 4xx read:: {len(new_list_4xx)}")
    logging.info(f"Failed 5xx read:: {len(new_list_5xx)}")

    if return_one_list:
        return new_list_4xx + new_list_5xx
    else:
        return new_list_4xx, new_list_5xx
