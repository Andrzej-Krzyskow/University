class BookNotFoundError(Exception):

    def __init__(self):
        super().__init__("\nBook with given ID was not found. Program will exit.")
