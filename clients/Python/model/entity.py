from .entity_type import EntityType
from .vec2_int import Vec2Int
class Entity:
    def __init__(self, id, player_id, entity_type, position, health, active):
        self.id = id
        self.player_id = player_id
        self.entity_type = entity_type
        self.position = position
        self.health = health
        self.active = active
    @staticmethod
    def read_from(stream):
        id = stream.read_int()
        if stream.read_bool():
            player_id = stream.read_int()
        else:
            player_id = None
        entity_type = EntityType(stream.read_int())
        position = Vec2Int.read_from(stream)
        health = stream.read_int()
        active = stream.read_bool()
        return Entity(id, player_id, entity_type, position, health, active)
    def write_to(self, stream):
        stream.write_int(self.id)
        if self.player_id is None:
            stream.write_bool(False)
        else:
            stream.write_bool(True)
            stream.write_int(self.player_id)
        stream.write_int(self.entity_type)
        self.position.write_to(stream)
        stream.write_int(self.health)
        stream.write_bool(self.active)
    def __repr__(self):
        return "Entity(" + \
            repr(self.id) + "," + \
            repr(self.player_id) + "," + \
            repr(self.entity_type) + "," + \
            repr(self.position) + "," + \
            repr(self.health) + "," + \
            repr(self.active) + \
            ")"
