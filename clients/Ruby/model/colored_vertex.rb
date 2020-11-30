require_relative 'vec2_float'
require_relative 'vec2_float'
require_relative 'color'
class ColoredVertex
    attr_accessor :world_pos
    attr_accessor :screen_offset
    attr_accessor :color
    def initialize(world_pos, screen_offset, color)
        @world_pos = world_pos
        @screen_offset = screen_offset
        @color = color
    end
    def self.read_from(stream)
        if stream.read_bool()
            world_pos = Vec2Float.read_from(stream)
        else
            world_pos = nil
        end
        screen_offset = Vec2Float.read_from(stream)
        color = Color.read_from(stream)
        ColoredVertex.new(world_pos, screen_offset, color)
    end
    def write_to(stream)
        if @world_pos.nil?
            stream.write_bool(false)
        else
            stream.write_bool(true)
            @world_pos.write_to(stream)
        end
        @screen_offset.write_to(stream)
        @color.write_to(stream)
    end
end
