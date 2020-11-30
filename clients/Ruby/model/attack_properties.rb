class AttackProperties
    attr_accessor :attack_range
    attr_accessor :damage
    attr_accessor :collect_resource
    def initialize(attack_range, damage, collect_resource)
        @attack_range = attack_range
        @damage = damage
        @collect_resource = collect_resource
    end
    def self.read_from(stream)
        attack_range = stream.read_int()
        damage = stream.read_int()
        collect_resource = stream.read_bool()
        AttackProperties.new(attack_range, damage, collect_resource)
    end
    def write_to(stream)
        stream.write_int(@attack_range)
        stream.write_int(@damage)
        stream.write_bool(@collect_resource)
    end
end
