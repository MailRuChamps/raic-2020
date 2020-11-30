require_relative 'entity_type'
require_relative 'vec2_int'
class BuildAction
    attr_accessor :entity_type
    attr_accessor :position
    def initialize(entity_type, position)
        @entity_type = entity_type
        @position = position
    end
    def self.read_from(stream)
        entity_type = stream.read_int()
        if entity_type < 0 || entity_type > 10
            raise "Unexpected tag value"
        end
        position = Vec2Int.read_from(stream)
        BuildAction.new(entity_type, position)
    end
    def write_to(stream)
        stream.write_int(@entity_type)
        @position.write_to(stream)
    end
end
