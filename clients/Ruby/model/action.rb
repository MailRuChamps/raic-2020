require_relative 'entity_action'
class Action
    attr_accessor :entity_actions
    def initialize(entity_actions)
        @entity_actions = entity_actions
    end
    def self.read_from(stream)
        entity_actions = Hash.new
        stream.read_int().times do |_|
            entity_actions_key = stream.read_int()
            entity_actions_value = EntityAction.read_from(stream)
            entity_actions[entity_actions_key] = entity_actions_value
        end
        Action.new(entity_actions)
    end
    def write_to(stream)
        stream.write_int(@entity_actions.length())
        @entity_actions.each do |key, value|
            stream.write_int(key)
            value.write_to(stream)
        end
    end
end
