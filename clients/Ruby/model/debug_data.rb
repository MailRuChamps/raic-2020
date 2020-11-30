require_relative 'colored_vertex'
require_relative 'primitive_type'
require_relative 'colored_vertex'
class DebugData
    def self.read_from(stream)
        tag = stream.read_int()
        if tag == DebugData::Log::TAG
            return DebugData::Log.read_from(stream)
        end
        if tag == DebugData::Primitives::TAG
            return DebugData::Primitives.read_from(stream)
        end
        if tag == DebugData::PlacedText::TAG
            return DebugData::PlacedText.read_from(stream)
        end
        raise "Unexpected tag value"
    end

    class Log
        TAG = 0
        attr_accessor :text
        def initialize(text)
            @text = text
        end
        def self.read_from(stream)
            text = stream.read_string()
            Log.new(text)
        end
        def write_to(stream)
            stream.write_int(TAG)
            stream.write_string(@text)
        end
    end
    class Primitives
        TAG = 1
        attr_accessor :vertices
        attr_accessor :primitive_type
        def initialize(vertices, primitive_type)
            @vertices = vertices
            @primitive_type = primitive_type
        end
        def self.read_from(stream)
            vertices = []
            stream.read_int().times do |_|
                vertices_element = ColoredVertex.read_from(stream)
                vertices.push(vertices_element)
            end
            primitive_type = stream.read_int()
            if primitive_type < 0 || primitive_type > 2
                raise "Unexpected tag value"
            end
            Primitives.new(vertices, primitive_type)
        end
        def write_to(stream)
            stream.write_int(TAG)
            stream.write_int(@vertices.length())
            @vertices.each do |element|
                element.write_to(stream)
            end
            stream.write_int(@primitive_type)
        end
    end
    class PlacedText
        TAG = 2
        attr_accessor :vertex
        attr_accessor :text
        attr_accessor :alignment
        attr_accessor :size
        def initialize(vertex, text, alignment, size)
            @vertex = vertex
            @text = text
            @alignment = alignment
            @size = size
        end
        def self.read_from(stream)
            vertex = ColoredVertex.read_from(stream)
            text = stream.read_string()
            alignment = stream.read_float()
            size = stream.read_float()
            PlacedText.new(vertex, text, alignment, size)
        end
        def write_to(stream)
            stream.write_int(TAG)
            @vertex.write_to(stream)
            stream.write_string(@text)
            stream.write_float(@alignment)
            stream.write_float(@size)
        end
    end
end
