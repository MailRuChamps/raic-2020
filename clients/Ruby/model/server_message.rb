require_relative 'player_view'
require_relative 'player_view'
class ServerMessage
    def self.read_from(stream)
        tag = stream.read_int()
        if tag == ServerMessage::GetAction::TAG
            return ServerMessage::GetAction.read_from(stream)
        end
        if tag == ServerMessage::Finish::TAG
            return ServerMessage::Finish.read_from(stream)
        end
        if tag == ServerMessage::DebugUpdate::TAG
            return ServerMessage::DebugUpdate.read_from(stream)
        end
        raise "Unexpected tag value"
    end

    class GetAction
        TAG = 0
        attr_accessor :player_view
        attr_accessor :debug_available
        def initialize(player_view, debug_available)
            @player_view = player_view
            @debug_available = debug_available
        end
        def self.read_from(stream)
            player_view = PlayerView.read_from(stream)
            debug_available = stream.read_bool()
            GetAction.new(player_view, debug_available)
        end
        def write_to(stream)
            stream.write_int(TAG)
            @player_view.write_to(stream)
            stream.write_bool(@debug_available)
        end
    end
    class Finish
        TAG = 1
        def initialize()
        end
        def self.read_from(stream)
            Finish.new()
        end
        def write_to(stream)
            stream.write_int(TAG)
        end
    end
    class DebugUpdate
        TAG = 2
        attr_accessor :player_view
        def initialize(player_view)
            @player_view = player_view
        end
        def self.read_from(stream)
            player_view = PlayerView.read_from(stream)
            DebugUpdate.new(player_view)
        end
        def write_to(stream)
            stream.write_int(TAG)
            @player_view.write_to(stream)
        end
    end
end
