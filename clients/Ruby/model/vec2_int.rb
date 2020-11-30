class Vec2Int
    attr_accessor :x
    attr_accessor :y
    def initialize(x, y)
        @x = x
        @y = y
    end
    def self.read_from(stream)
        x = stream.read_int()
        y = stream.read_int()
        Vec2Int.new(x, y)
    end
    def write_to(stream)
        stream.write_int(@x)
        stream.write_int(@y)
    end
end
