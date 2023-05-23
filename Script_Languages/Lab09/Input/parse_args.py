import argparse


def parse_args():
    parser = argparse.ArgumentParser(add_help=False)

    parser.add_argument("filename",
                        help="Name of a csv file with the dataset",
                        type=str
                        )

    parser.add_argument("-o", "--excel",
                        help="Name of an excel file to be created",
                        type=str
                        )

    parser.add_argument('-h', '--help',
                        action='help',
                        default=argparse.SUPPRESS,
                        help='Show this help message and exit.'
                        )

    return parser.parse_args()
