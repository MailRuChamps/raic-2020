require_relative 'move_action'
require_relative 'build_action'
require_relative 'attack_action'
require_relative 'repair_action'
class EntityAction
    attr_accessor :move_action
    attr_accessor :build_action
    attr_accessor :attack_action
    attr_accessor :repair_action
    def initialize(move_action, build_action, attack_action, repair_action)
        @move_action = move_action
        @build_action = build_action
        @attack_action = attack_action
        @repair_action = repair_action
    end
    def self.read_from(stream)
        if stream.read_bool()
            move_action = MoveAction.read_from(stream)
        else
            move_action = nil
        end
        if stream.read_bool()
            build_action = BuildAction.read_from(stream)
        else
            build_action = nil
        end
        if stream.read_bool()
            attack_action = AttackAction.read_from(stream)
        else
            attack_action = nil
        end
        if stream.read_bool()
            repair_action = RepairAction.read_from(stream)
        else
            repair_action = nil
        end
        EntityAction.new(move_action, build_action, attack_action, repair_action)
    end
    def write_to(stream)
        if @move_action.nil?
            stream.write_bool(false)
        else
            stream.write_bool(true)
            @move_action.write_to(stream)
        end
        if @build_action.nil?
            stream.write_bool(false)
        else
            stream.write_bool(true)
            @build_action.write_to(stream)
        end
        if @attack_action.nil?
            stream.write_bool(false)
        else
            stream.write_bool(true)
            @attack_action.write_to(stream)
        end
        if @repair_action.nil?
            stream.write_bool(false)
        else
            stream.write_bool(true)
            @repair_action.write_to(stream)
        end
    end
end
