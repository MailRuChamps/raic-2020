require_relative 'model'

class MyStrategy
    def get_action(player_view, debug_interface)
        return Action.new(Hash.new)
    end
    def debug_update(player_view, debug_interface)
        debug_interface.send(DebugCommand::Clear.new())
        debug_interface.get_state()
    end
end