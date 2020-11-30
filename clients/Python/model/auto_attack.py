from .entity_type import EntityType
class AutoAttack:
    def __init__(self, pathfind_range, valid_targets):
        self.pathfind_range = pathfind_range
        self.valid_targets = valid_targets
    @staticmethod
    def read_from(stream):
        pathfind_range = stream.read_int()
        valid_targets = []
        for _ in range(stream.read_int()):
            valid_targets_element = EntityType(stream.read_int())
            valid_targets.append(valid_targets_element)
        return AutoAttack(pathfind_range, valid_targets)
    def write_to(self, stream):
        stream.write_int(self.pathfind_range)
        stream.write_int(len(self.valid_targets))
        for element in self.valid_targets:
            stream.write_int(element)
    def __repr__(self):
        return "AutoAttack(" + \
            repr(self.pathfind_range) + "," + \
            repr(self.valid_targets) + \
            ")"
