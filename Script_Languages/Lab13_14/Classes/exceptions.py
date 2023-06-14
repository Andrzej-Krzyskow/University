class NoJsonFileFoundError(Exception):

    def __init__(self):
        super().__init__("\nDataset does not contain \".json\" file.")


class DatabaseNotFoundError(Exception):

    def __init__(self):
        super().__init__("\nDatabase not found.")


class MissingHeadersError(Exception):

    def __init__(self):
        super().__init__("\nHeaders not found.")


class JsonNotSupportedError(Exception):

    def __init__(self):
        super().__init__("\nJSON format is unusual thus not supported.")
