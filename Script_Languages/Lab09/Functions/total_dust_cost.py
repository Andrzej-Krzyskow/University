def total_dust_cost(data):
    if len(data) == 0:
        return 0

    sum = 0

    for row in data:
        try:
            if len(row.dust) > 0:
                sum += int(row.dust[1: row.dust.find(',')])
        except ValueError:
            pass

    return sum
