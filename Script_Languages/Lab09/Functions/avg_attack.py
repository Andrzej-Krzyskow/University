def avg_attack(data):
    if len(data) == 0:
        return 0

    sum = 0
    counter = 0

    for row in data:
        try:
            sum += int(row.attack)
            counter += 1
        except ValueError:
            pass

    return sum / counter
