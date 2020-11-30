import model


class MyStrategy:
    def get_action(self, player_view, debug_interface):
        return model.Action({})

    def debug_update(self, player_view, debug_interface):
        debug_interface.send(model.DebugCommand.Clear())
        debug_interface.get_state()
