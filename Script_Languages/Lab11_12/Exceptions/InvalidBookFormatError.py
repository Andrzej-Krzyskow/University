class InvalidBookFormatError(Exception):

    def __init__(self):
        super().__init__("\nInvalid book format. Program will exit.")
