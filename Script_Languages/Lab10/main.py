import os

import click as click

from Functions.create_xlsx import create_xlsx
from Functions.get_cat_facts import get_cat_facts
from Functions.parse_teachers import parse_teachers
from Functions.send_email import send_email
from Input.read_credentials import read_credentials


def run_email(mail, topic, to):
    credentials = read_credentials("credentials.json")
    send_email(credentials, (mail, topic, to))


def run_cats(cat_facts):
    get_cat_facts(cat_facts)


def run_parse(teachers):
    parse_teachers(teachers)


def run_send_teachers(teachers, send_to):
    teachers_list = parse_teachers(teachers)
    filename = "temp.xlsx"
    file = create_xlsx(teachers_list)
    credentials = read_credentials("credentials.json")

    send_email(credentials, ("", "List of teachers", send_to), filename)

    os.remove(filename)


@click.command()
@click.option("--mail", type=str, help="Your message to be sent")
@click.option("--topic", type=str, help="Topic of your message", default="Topic")
@click.option("--to", type=str, help="Recipient of your message")
@click.option("--cat-facts", type=int, help="Number of facts about cats")
@click.option("--teachers", type=str, help="Search for teachers with last name starting with given letter")
@click.option("--send-to", type=str, help="Recipient of your message")
def run(mail, topic, to, cat_facts, teachers, send_to):
    # run_email(mail, topic, to)
    # run_cats(cat_facts)
    run_parse(teachers)
    # run_send_teachers(teachers, send_to)


if __name__ == '__main__':
    run()
