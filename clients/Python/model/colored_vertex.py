from .vec2_float import Vec2Float
from .vec2_float import Vec2Float
from .color import Color
class ColoredVertex:
    def __init__(self, world_pos, screen_offset, color):
        self.world_pos = world_pos
        self.screen_offset = screen_offset
        self.color = color
    @staticmethod
    def read_from(stream):
        if stream.read_bool():
            world_pos = Vec2Float.read_from(stream)
        else:
            world_pos = None
        screen_offset = Vec2Float.read_from(stream)
        color = Color.read_from(stream)
        return ColoredVertex(world_pos, screen_offset, color)
    def write_to(self, stream):
        if self.world_pos is None:
            stream.write_bool(False)
        else:
            stream.write_bool(True)
            self.world_pos.write_to(stream)
        self.screen_offset.write_to(stream)
        self.color.write_to(stream)
    def __repr__(self):
        return "ColoredVertex(" + \
            repr(self.world_pos) + "," + \
            repr(self.screen_offset) + "," + \
            repr(self.color) + \
            ")"
