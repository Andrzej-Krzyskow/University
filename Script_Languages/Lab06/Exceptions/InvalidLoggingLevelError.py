class InvalidLoggingLevelError(Exception):
    def __init__(self):
        super().__init__("Invalid logging level.")