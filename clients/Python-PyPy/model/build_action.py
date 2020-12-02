from .entity_type import EntityType
from .vec2_int import Vec2Int
class BuildAction:
    def __init__(self, entity_type, position):
        self.entity_type = entity_type
        self.position = position
    @staticmethod
    def read_from(stream):
        entity_type = EntityType(stream.read_int())
        position = Vec2Int.read_from(stream)
        return BuildAction(entity_type, position)
    def write_to(self, stream):
        stream.write_int(self.entity_type)
        self.position.write_to(stream)
    def __repr__(self):
        return "BuildAction(" + \
            repr(self.entity_type) + "," + \
            repr(self.position) + \
            ")"
