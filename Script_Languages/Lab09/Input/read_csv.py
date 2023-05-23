import csv

from Classes.DataContainer import DataContainer
from Classes.DataRow import DataRow


def read_csv(filename):
    container = DataContainer()

    with open(filename) as f:
        csv_reader = csv.reader(f)
        next(csv_reader)

        for row in csv_reader:
            #print(row)
            container.add_row(DataRow(*row))

    return container
