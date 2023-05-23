import logging


def get_successful_reads(list_of_logs):
    logging.basicConfig(filename='read_debug.log', level=logging.INFO, filemode='a')

    new_list = [x for x in list_of_logs if 200 <= x[3] < 300]

    logging.info(f"Successful reads: {len(new_list)}")
    return new_list
