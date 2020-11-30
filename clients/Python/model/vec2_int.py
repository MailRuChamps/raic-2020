class Vec2Int:
    def __init__(self, x, y):
        self.x = x
        self.y = y
    @staticmethod
    def read_from(stream):
        x = stream.read_int()
        y = stream.read_int()
        return Vec2Int(x, y)
    def write_to(self, stream):
        stream.write_int(self.x)
        stream.write_int(self.y)
    def __repr__(self):
        return "Vec2Int(" + \
            repr(self.x) + "," + \
            repr(self.y) + \
            ")"
