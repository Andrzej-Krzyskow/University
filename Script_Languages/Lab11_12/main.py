import click
from hydra import initialize, compose

from Utils.book_scrapping import *
from Utils.docx_creating import create_docx


@click.command(context_settings=dict(help_option_names=["-h", "--help"]))
# @click.argument("id", type=str)
@click.option("--id", type=str, default="70877", help="ID of the book to be downloaded and processed",
              show_default=True)
def run(id):
    """
    \b
    Script downloads the book from gutenberg.org and process it.
    most books are not supported as the book's HTML structure
    is not standardized on gutenberg.org; script supports only
    books that have chapters inside .chapter class that is
    inside div container; example valid IDs: 70877, 70876, 70844,
    70744
    """

    with initialize(version_base=None, config_path="conf"):
        cfg = compose(config_name="config.yaml")

    if not id:
        id = cfg.params.id

    try:
        book = scrap_book(id, cfg)
    except (BookNotFoundError, InvalidBookFormatError) as e:
        print(e, file=sys.stderr)
        sys.exit()
    else:
        print("\nBook successfully scrapped.")

    create_docx(book, cfg)
    print("\nDocx report successfully created.")


if __name__ == '__main__':
    run()
