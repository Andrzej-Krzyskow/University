from Input.read_log import read_log


class AccessLog:

    def __init__(self):
        self._items = []

    def __getitem__(self, key):
        for k, v in self._items:
            if k == key:
                return v
        raise KeyError(f"Key {key} not found")

    def __setitem__(self, key, value):
        for i, (k, v) in enumerate(self._items):
            if k == key:
                self._items[i] = (key, value)
                return
        self._items.append((key, value))

    def __delitem__(self, key):
        for i, (k, v) in enumerate(self._items):
            if k == key:
                del self._items[i]
                return
        raise KeyError(f"Key {key} not found")

    def __contains__(self, key):
        for k, v in self._items:
            if k == key:
                return True
        return False

    def __iter__(self):
        return iter(self.keys())

    def __len__(self):
        return len(self._items)

    def __str__(self):
        items_str = ', '.join([f"'{k}': {v}" for k, v in self._items])
        return "{" + items_str + "}"

    def keys(self):
        return [k for k, v in self._items]

    def values(self):
        return [v for k, v in self._items]

    def items(self):
        return self._items

    @staticmethod
    def __logs_to_dict(logs):
        dictionary = AccessLog()
        for log in logs:
            if log.host in dictionary:
                dictionary[log.host].append(log)
            else:
                dictionary[log.host] = [log]

        return dictionary

    @classmethod
    def get_access_log(cls, file_name):
        list_of_logs = read_log(file_name)
        return cls.__logs_to_dict(list_of_logs)
