class MissingParametersError(Exception):
    def __init__(self):
        super().__init__("Incorrect parameters in json file.")