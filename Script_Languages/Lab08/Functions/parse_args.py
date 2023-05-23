import argparse


def parse_args():
    par = argparse.ArgumentParser()

    par.add_argument("-ip", "--ip_address",
                     help="Source IP address",
                     type=str,
                     default="127.0.0.1"
                     )

    par.add_argument("-id", "--id_number",
                     help="ID number of a student",
                     type=str,
                     default="111111"
                     )

    return par.parse_args()
