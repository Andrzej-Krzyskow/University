class InvalidHttpMethodError(Exception):
    def __init__(self):
        super().__init__("Invalid HTTP method.")
