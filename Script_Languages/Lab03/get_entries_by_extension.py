import logging
from get_successful_reads import get_successful_reads


def get_entries_success_by_extension(list_of_logs, extension):
    logging.basicConfig(filename='read_debug.log', level=logging.INFO, filemode='a')

    list_of_logs = get_successful_reads(list_of_logs)

    new_list = [x for x in list_of_logs if str(x[2]).endswith(extension)]

    logging.info(f"Number of entries with extension {extension}: {len(new_list)}")

    return new_list
