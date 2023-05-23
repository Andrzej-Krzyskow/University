def count_types(data):
    counters = {}

    for row in data:
        type = row.type

        if type not in counters:
            counters[type] = 0

        counters[type] += 1

    return counters
