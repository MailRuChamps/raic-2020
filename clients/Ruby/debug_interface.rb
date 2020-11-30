require_relative 'model'

class DebugInterface
    def initialize(reader, writer)
        @reader = reader
        @writer = writer
    end

    def send(command)
        ClientMessage::DebugMessage.new(command).write_to(@writer)
        @writer.flush()
    end

    def get_state()
        ClientMessage::RequestDebugState.new().write_to(@writer)
        @writer.flush()
        return DebugState.read_from(@reader)
    end
end