import logging


def get_entries_by_code(list_of_logs, code):
    logging.basicConfig(filename='read_debug.log', level=logging.INFO, filemode='a')

    if not 200 <= code < 600:
        logging.warning("Incorrect html code")
        return []

    new_list = [x for x in list_of_logs if x[3] == code]

    logging.info(f"Number of entries with code {code}: {len(new_list)}")

    new_list.sort(key=lambda x: (x[1], x[-1]))
    # 'sorted()' -> returns a new list
    # 'sort()' -> sorts in place, does NOT return a new list
    return new_list
