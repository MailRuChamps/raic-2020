require_relative 'entity_type'
require_relative 'vec2_int'
class Entity
    attr_accessor :id
    attr_accessor :player_id
    attr_accessor :entity_type
    attr_accessor :position
    attr_accessor :health
    attr_accessor :active
    def initialize(id, player_id, entity_type, position, health, active)
        @id = id
        @player_id = player_id
        @entity_type = entity_type
        @position = position
        @health = health
        @active = active
    end
    def self.read_from(stream)
        id = stream.read_int()
        if stream.read_bool()
            player_id = stream.read_int()
        else
            player_id = nil
        end
        entity_type = stream.read_int()
        if entity_type < 0 || entity_type > 10
            raise "Unexpected tag value"
        end
        position = Vec2Int.read_from(stream)
        health = stream.read_int()
        active = stream.read_bool()
        Entity.new(id, player_id, entity_type, position, health, active)
    end
    def write_to(stream)
        stream.write_int(@id)
        if @player_id.nil?
            stream.write_bool(false)
        else
            stream.write_bool(true)
            stream.write_int(@player_id)
        end
        stream.write_int(@entity_type)
        @position.write_to(stream)
        stream.write_int(@health)
        stream.write_bool(@active)
    end
end
