from .vec2_float import Vec2Float
class Camera:
    def __init__(self, center, rotation, attack, distance, perspective):
        self.center = center
        self.rotation = rotation
        self.attack = attack
        self.distance = distance
        self.perspective = perspective
    @staticmethod
    def read_from(stream):
        center = Vec2Float.read_from(stream)
        rotation = stream.read_float()
        attack = stream.read_float()
        distance = stream.read_float()
        perspective = stream.read_bool()
        return Camera(center, rotation, attack, distance, perspective)
    def write_to(self, stream):
        self.center.write_to(stream)
        stream.write_float(self.rotation)
        stream.write_float(self.attack)
        stream.write_float(self.distance)
        stream.write_bool(self.perspective)
    def __repr__(self):
        return "Camera(" + \
            repr(self.center) + "," + \
            repr(self.rotation) + "," + \
            repr(self.attack) + "," + \
            repr(self.distance) + "," + \
            repr(self.perspective) + \
            ")"
