class RepairAction:
    def __init__(self, target):
        self.target = target
    @staticmethod
    def read_from(stream):
        target = stream.read_int()
        return RepairAction(target)
    def write_to(self, stream):
        stream.write_int(self.target)
    def __repr__(self):
        return "RepairAction(" + \
            repr(self.target) + \
            ")"
