class DebugCommand:
    @staticmethod
    def read_from(stream):
        tag = stream.read_int()
        if tag == Add.TAG:
            return DebugCommand.Add.read_from(stream)
        if tag == Clear.TAG:
            return DebugCommand.Clear.read_from(stream)
        raise Exception("Unexpected tag value")

from .debug_data import DebugData
class Add(DebugCommand):
    TAG = 0
    def __init__(self, data):
        self.data = data
    @staticmethod
    def read_from(stream):
        data = DebugData.read_from(stream)
        return Add(data)
    def write_to(self, stream):
        stream.write_int(self.TAG)
        self.data.write_to(stream)
    def __repr__(self):
        return "Add(" + \
            repr(self.data) + \
            ")"
DebugCommand.Add = Add
class Clear(DebugCommand):
    TAG = 1
    def __init__(self):
        pass
    @staticmethod
    def read_from(stream):
        return Clear()
    def write_to(self, stream):
        stream.write_int(self.TAG)
    def __repr__(self):
        return "Clear(" + \
            ")"
DebugCommand.Clear = Clear
