require_relative 'entity_type'
require_relative 'entity_properties'
require_relative 'player'
require_relative 'entity'
class PlayerView
    attr_accessor :my_id
    attr_accessor :map_size
    attr_accessor :fog_of_war
    attr_accessor :entity_properties
    attr_accessor :max_tick_count
    attr_accessor :max_pathfind_nodes
    attr_accessor :current_tick
    attr_accessor :players
    attr_accessor :entities
    def initialize(my_id, map_size, fog_of_war, entity_properties, max_tick_count, max_pathfind_nodes, current_tick, players, entities)
        @my_id = my_id
        @map_size = map_size
        @fog_of_war = fog_of_war
        @entity_properties = entity_properties
        @max_tick_count = max_tick_count
        @max_pathfind_nodes = max_pathfind_nodes
        @current_tick = current_tick
        @players = players
        @entities = entities
    end
    def self.read_from(stream)
        my_id = stream.read_int()
        map_size = stream.read_int()
        fog_of_war = stream.read_bool()
        entity_properties = Hash.new
        stream.read_int().times do |_|
            entity_properties_key = stream.read_int()
            if entity_properties_key < 0 || entity_properties_key > 10
                raise "Unexpected tag value"
            end
            entity_properties_value = EntityProperties.read_from(stream)
            entity_properties[entity_properties_key] = entity_properties_value
        end
        max_tick_count = stream.read_int()
        max_pathfind_nodes = stream.read_int()
        current_tick = stream.read_int()
        players = []
        stream.read_int().times do |_|
            players_element = Player.read_from(stream)
            players.push(players_element)
        end
        entities = []
        stream.read_int().times do |_|
            entities_element = Entity.read_from(stream)
            entities.push(entities_element)
        end
        PlayerView.new(my_id, map_size, fog_of_war, entity_properties, max_tick_count, max_pathfind_nodes, current_tick, players, entities)
    end
    def write_to(stream)
        stream.write_int(@my_id)
        stream.write_int(@map_size)
        stream.write_bool(@fog_of_war)
        stream.write_int(@entity_properties.length())
        @entity_properties.each do |key, value|
            stream.write_int(key)
            value.write_to(stream)
        end
        stream.write_int(@max_tick_count)
        stream.write_int(@max_pathfind_nodes)
        stream.write_int(@current_tick)
        stream.write_int(@players.length())
        @players.each do |element|
            element.write_to(stream)
        end
        stream.write_int(@entities.length())
        @entities.each do |element|
            element.write_to(stream)
        end
    end
end
