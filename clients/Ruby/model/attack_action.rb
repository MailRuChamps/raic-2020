require_relative 'auto_attack'
class AttackAction
    attr_accessor :target
    attr_accessor :auto_attack
    def initialize(target, auto_attack)
        @target = target
        @auto_attack = auto_attack
    end
    def self.read_from(stream)
        if stream.read_bool()
            target = stream.read_int()
        else
            target = nil
        end
        if stream.read_bool()
            auto_attack = AutoAttack.read_from(stream)
        else
            auto_attack = nil
        end
        AttackAction.new(target, auto_attack)
    end
    def write_to(stream)
        if @target.nil?
            stream.write_bool(false)
        else
            stream.write_bool(true)
            stream.write_int(@target)
        end
        if @auto_attack.nil?
            stream.write_bool(false)
        else
            stream.write_bool(true)
            @auto_attack.write_to(stream)
        end
    end
end
