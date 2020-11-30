class RepairAction
    attr_accessor :target
    def initialize(target)
        @target = target
    end
    def self.read_from(stream)
        target = stream.read_int()
        RepairAction.new(target)
    end
    def write_to(stream)
        stream.write_int(@target)
    end
end
