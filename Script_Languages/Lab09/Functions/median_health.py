import statistics


def median_health(data):
    health = [int(x.health) for x in data if str(x.health).isnumeric()]

    return statistics.median(health)
