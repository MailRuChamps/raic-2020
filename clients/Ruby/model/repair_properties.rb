require_relative 'entity_type'
class RepairProperties
    attr_accessor :valid_targets
    attr_accessor :power
    def initialize(valid_targets, power)
        @valid_targets = valid_targets
        @power = power
    end
    def self.read_from(stream)
        valid_targets = []
        stream.read_int().times do |_|
            valid_targets_element = stream.read_int()
            if valid_targets_element < 0 || valid_targets_element > 10
                raise "Unexpected tag value"
            end
            valid_targets.push(valid_targets_element)
        end
        power = stream.read_int()
        RepairProperties.new(valid_targets, power)
    end
    def write_to(stream)
        stream.write_int(@valid_targets.length())
        @valid_targets.each do |element|
            stream.write_int(element)
        end
        stream.write_int(@power)
    end
end
