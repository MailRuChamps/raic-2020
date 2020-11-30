require_relative 'debug_data'
class DebugCommand
    def self.read_from(stream)
        tag = stream.read_int()
        if tag == DebugCommand::Add::TAG
            return DebugCommand::Add.read_from(stream)
        end
        if tag == DebugCommand::Clear::TAG
            return DebugCommand::Clear.read_from(stream)
        end
        raise "Unexpected tag value"
    end

    class Add
        TAG = 0
        attr_accessor :data
        def initialize(data)
            @data = data
        end
        def self.read_from(stream)
            data = DebugData.read_from(stream)
            Add.new(data)
        end
        def write_to(stream)
            stream.write_int(TAG)
            @data.write_to(stream)
        end
    end
    class Clear
        TAG = 1
        def initialize()
        end
        def self.read_from(stream)
            Clear.new()
        end
        def write_to(stream)
            stream.write_int(TAG)
        end
    end
end
