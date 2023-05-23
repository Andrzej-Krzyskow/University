import os

import click

from Input.read_csv import read_csv
from Output.print_to_console import print_to_console
from Output.print_to_xlsx import print_to_xlsx


@click.command()
@click.argument("filename", type=str)
@click.option("-o", "--excel", type=str, help="Name of an excel file to be created")
def run(filename, excel):
    """
    Process a CSV file.

    FILENAME: Name of a CSV file with the dataset.
    """
    if not filename.endswith(".csv"):
        click.echo("Invalid file format. Only CSV files are supported.")
        return

    if not os.path.exists(filename):
        click.echo("CSV file not found. Program will stop.")
        return

    rows_obj = read_csv(filename)

    if excel:
        print_to_xlsx(rows_obj.rows, excel)
    else:
        print_to_console(rows_obj.rows)


if __name__ == '__main__':
    run()
