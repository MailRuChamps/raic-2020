class ServerMessage:
    @staticmethod
    def read_from(stream):
        tag = stream.read_int()
        if tag == GetAction.TAG:
            return ServerMessage.GetAction.read_from(stream)
        if tag == Finish.TAG:
            return ServerMessage.Finish.read_from(stream)
        if tag == DebugUpdate.TAG:
            return ServerMessage.DebugUpdate.read_from(stream)
        raise Exception("Unexpected tag value")

from .player_view import PlayerView
class GetAction(ServerMessage):
    TAG = 0
    def __init__(self, player_view, debug_available):
        self.player_view = player_view
        self.debug_available = debug_available
    @staticmethod
    def read_from(stream):
        player_view = PlayerView.read_from(stream)
        debug_available = stream.read_bool()
        return GetAction(player_view, debug_available)
    def write_to(self, stream):
        stream.write_int(self.TAG)
        self.player_view.write_to(stream)
        stream.write_bool(self.debug_available)
    def __repr__(self):
        return "GetAction(" + \
            repr(self.player_view) + "," + \
            repr(self.debug_available) + \
            ")"
ServerMessage.GetAction = GetAction
class Finish(ServerMessage):
    TAG = 1
    def __init__(self):
        pass
    @staticmethod
    def read_from(stream):
        return Finish()
    def write_to(self, stream):
        stream.write_int(self.TAG)
    def __repr__(self):
        return "Finish(" + \
            ")"
ServerMessage.Finish = Finish
from .player_view import PlayerView
class DebugUpdate(ServerMessage):
    TAG = 2
    def __init__(self, player_view):
        self.player_view = player_view
    @staticmethod
    def read_from(stream):
        player_view = PlayerView.read_from(stream)
        return DebugUpdate(player_view)
    def write_to(self, stream):
        stream.write_int(self.TAG)
        self.player_view.write_to(stream)
    def __repr__(self):
        return "DebugUpdate(" + \
            repr(self.player_view) + \
            ")"
ServerMessage.DebugUpdate = DebugUpdate
