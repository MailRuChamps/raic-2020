require_relative 'entity_type'
class AutoAttack
    attr_accessor :pathfind_range
    attr_accessor :valid_targets
    def initialize(pathfind_range, valid_targets)
        @pathfind_range = pathfind_range
        @valid_targets = valid_targets
    end
    def self.read_from(stream)
        pathfind_range = stream.read_int()
        valid_targets = []
        stream.read_int().times do |_|
            valid_targets_element = stream.read_int()
            if valid_targets_element < 0 || valid_targets_element > 10
                raise "Unexpected tag value"
            end
            valid_targets.push(valid_targets_element)
        end
        AutoAttack.new(pathfind_range, valid_targets)
    end
    def write_to(stream)
        stream.write_int(@pathfind_range)
        stream.write_int(@valid_targets.length())
        @valid_targets.each do |element|
            stream.write_int(element)
        end
    end
end
