import os

from Input.parse_args import parse_args
from Input.read_csv import read_csv
from Output.print_to_console import print_to_console
from Output.print_to_xlsx import print_to_xlsx


def run():
    args = parse_args()

    if not (args.filename.endswith(".csv") and os.path.exists(args.filename)):
        print("CSV file not found, program will stop.")
        exit(1)

    rows_obj = read_csv(args.filename)

    if args.excel:
        print_to_xlsx(rows_obj.rows, args.excel)
    else:
        print_to_console(rows_obj.rows)


if __name__ == '__main__':
    run()
