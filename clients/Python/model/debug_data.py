class DebugData:
    @staticmethod
    def read_from(stream):
        tag = stream.read_int()
        if tag == Log.TAG:
            return DebugData.Log.read_from(stream)
        if tag == Primitives.TAG:
            return DebugData.Primitives.read_from(stream)
        if tag == PlacedText.TAG:
            return DebugData.PlacedText.read_from(stream)
        raise Exception("Unexpected tag value")

class Log(DebugData):
    TAG = 0
    def __init__(self, text):
        self.text = text
    @staticmethod
    def read_from(stream):
        text = stream.read_string()
        return Log(text)
    def write_to(self, stream):
        stream.write_int(self.TAG)
        stream.write_string(self.text)
    def __repr__(self):
        return "Log(" + \
            repr(self.text) + \
            ")"
DebugData.Log = Log
from .colored_vertex import ColoredVertex
from .primitive_type import PrimitiveType
class Primitives(DebugData):
    TAG = 1
    def __init__(self, vertices, primitive_type):
        self.vertices = vertices
        self.primitive_type = primitive_type
    @staticmethod
    def read_from(stream):
        vertices = []
        for _ in range(stream.read_int()):
            vertices_element = ColoredVertex.read_from(stream)
            vertices.append(vertices_element)
        primitive_type = PrimitiveType(stream.read_int())
        return Primitives(vertices, primitive_type)
    def write_to(self, stream):
        stream.write_int(self.TAG)
        stream.write_int(len(self.vertices))
        for element in self.vertices:
            element.write_to(stream)
        stream.write_int(self.primitive_type)
    def __repr__(self):
        return "Primitives(" + \
            repr(self.vertices) + "," + \
            repr(self.primitive_type) + \
            ")"
DebugData.Primitives = Primitives
from .colored_vertex import ColoredVertex
class PlacedText(DebugData):
    TAG = 2
    def __init__(self, vertex, text, alignment, size):
        self.vertex = vertex
        self.text = text
        self.alignment = alignment
        self.size = size
    @staticmethod
    def read_from(stream):
        vertex = ColoredVertex.read_from(stream)
        text = stream.read_string()
        alignment = stream.read_float()
        size = stream.read_float()
        return PlacedText(vertex, text, alignment, size)
    def write_to(self, stream):
        stream.write_int(self.TAG)
        self.vertex.write_to(stream)
        stream.write_string(self.text)
        stream.write_float(self.alignment)
        stream.write_float(self.size)
    def __repr__(self):
        return "PlacedText(" + \
            repr(self.vertex) + "," + \
            repr(self.text) + "," + \
            repr(self.alignment) + "," + \
            repr(self.size) + \
            ")"
DebugData.PlacedText = PlacedText
