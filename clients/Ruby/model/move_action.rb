require_relative 'vec2_int'
class MoveAction
    attr_accessor :target
    attr_accessor :find_closest_position
    attr_accessor :break_through
    def initialize(target, find_closest_position, break_through)
        @target = target
        @find_closest_position = find_closest_position
        @break_through = break_through
    end
    def self.read_from(stream)
        target = Vec2Int.read_from(stream)
        find_closest_position = stream.read_bool()
        break_through = stream.read_bool()
        MoveAction.new(target, find_closest_position, break_through)
    end
    def write_to(stream)
        @target.write_to(stream)
        stream.write_bool(@find_closest_position)
        stream.write_bool(@break_through)
    end
end
