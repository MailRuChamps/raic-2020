from .entity_type import EntityType
class RepairProperties:
    def __init__(self, valid_targets, power):
        self.valid_targets = valid_targets
        self.power = power
    @staticmethod
    def read_from(stream):
        valid_targets = []
        for _ in range(stream.read_int()):
            valid_targets_element = EntityType(stream.read_int())
            valid_targets.append(valid_targets_element)
        power = stream.read_int()
        return RepairProperties(valid_targets, power)
    def write_to(self, stream):
        stream.write_int(len(self.valid_targets))
        for element in self.valid_targets:
            stream.write_int(element)
        stream.write_int(self.power)
    def __repr__(self):
        return "RepairProperties(" + \
            repr(self.valid_targets) + "," + \
            repr(self.power) + \
            ")"
