require_relative 'vec2_float'
class Camera
    attr_accessor :center
    attr_accessor :rotation
    attr_accessor :attack
    attr_accessor :distance
    attr_accessor :perspective
    def initialize(center, rotation, attack, distance, perspective)
        @center = center
        @rotation = rotation
        @attack = attack
        @distance = distance
        @perspective = perspective
    end
    def self.read_from(stream)
        center = Vec2Float.read_from(stream)
        rotation = stream.read_float()
        attack = stream.read_float()
        distance = stream.read_float()
        perspective = stream.read_bool()
        Camera.new(center, rotation, attack, distance, perspective)
    end
    def write_to(stream)
        @center.write_to(stream)
        stream.write_float(@rotation)
        stream.write_float(@attack)
        stream.write_float(@distance)
        stream.write_bool(@perspective)
    end
end
