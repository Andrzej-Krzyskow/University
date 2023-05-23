class LogFileNotFoundError\
            (Exception):
    def __init__(self):
        super().__init__("Log file not found.")