def count_costs(data):
    counters = {}

    for row in data:
        cost = row.cost

        if cost not in counters:
            counters[cost] = 0

        counters[cost] += 1

    return counters
