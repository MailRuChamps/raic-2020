require_relative 'debug_command'
require_relative 'action'
class ClientMessage
    def self.read_from(stream)
        tag = stream.read_int()
        if tag == ClientMessage::DebugMessage::TAG
            return ClientMessage::DebugMessage.read_from(stream)
        end
        if tag == ClientMessage::ActionMessage::TAG
            return ClientMessage::ActionMessage.read_from(stream)
        end
        if tag == ClientMessage::DebugUpdateDone::TAG
            return ClientMessage::DebugUpdateDone.read_from(stream)
        end
        if tag == ClientMessage::RequestDebugState::TAG
            return ClientMessage::RequestDebugState.read_from(stream)
        end
        raise "Unexpected tag value"
    end

    class DebugMessage
        TAG = 0
        attr_accessor :command
        def initialize(command)
            @command = command
        end
        def self.read_from(stream)
            command = DebugCommand.read_from(stream)
            DebugMessage.new(command)
        end
        def write_to(stream)
            stream.write_int(TAG)
            @command.write_to(stream)
        end
    end
    class ActionMessage
        TAG = 1
        attr_accessor :action
        def initialize(action)
            @action = action
        end
        def self.read_from(stream)
            action = Action.read_from(stream)
            ActionMessage.new(action)
        end
        def write_to(stream)
            stream.write_int(TAG)
            @action.write_to(stream)
        end
    end
    class DebugUpdateDone
        TAG = 2
        def initialize()
        end
        def self.read_from(stream)
            DebugUpdateDone.new()
        end
        def write_to(stream)
            stream.write_int(TAG)
        end
    end
    class RequestDebugState
        TAG = 3
        def initialize()
        end
        def self.read_from(stream)
            RequestDebugState.new()
        end
        def write_to(stream)
            stream.write_int(TAG)
        end
    end
end
