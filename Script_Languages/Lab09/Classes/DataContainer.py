class DataContainer:

    def __init__(self):
        self.rows = []

    def add_row(self, row):
        self.rows.append(row)

    def __repr__(self):
        return f"DataContainer(rows={self.rows})"

    def __len__(self):
        return len(self.rows)
