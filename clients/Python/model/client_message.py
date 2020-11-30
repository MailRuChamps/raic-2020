class ClientMessage:
    @staticmethod
    def read_from(stream):
        tag = stream.read_int()
        if tag == DebugMessage.TAG:
            return ClientMessage.DebugMessage.read_from(stream)
        if tag == ActionMessage.TAG:
            return ClientMessage.ActionMessage.read_from(stream)
        if tag == DebugUpdateDone.TAG:
            return ClientMessage.DebugUpdateDone.read_from(stream)
        if tag == RequestDebugState.TAG:
            return ClientMessage.RequestDebugState.read_from(stream)
        raise Exception("Unexpected tag value")

from .debug_command import DebugCommand
class DebugMessage(ClientMessage):
    TAG = 0
    def __init__(self, command):
        self.command = command
    @staticmethod
    def read_from(stream):
        command = DebugCommand.read_from(stream)
        return DebugMessage(command)
    def write_to(self, stream):
        stream.write_int(self.TAG)
        self.command.write_to(stream)
    def __repr__(self):
        return "DebugMessage(" + \
            repr(self.command) + \
            ")"
ClientMessage.DebugMessage = DebugMessage
from .action import Action
class ActionMessage(ClientMessage):
    TAG = 1
    def __init__(self, action):
        self.action = action
    @staticmethod
    def read_from(stream):
        action = Action.read_from(stream)
        return ActionMessage(action)
    def write_to(self, stream):
        stream.write_int(self.TAG)
        self.action.write_to(stream)
    def __repr__(self):
        return "ActionMessage(" + \
            repr(self.action) + \
            ")"
ClientMessage.ActionMessage = ActionMessage
class DebugUpdateDone(ClientMessage):
    TAG = 2
    def __init__(self):
        pass
    @staticmethod
    def read_from(stream):
        return DebugUpdateDone()
    def write_to(self, stream):
        stream.write_int(self.TAG)
    def __repr__(self):
        return "DebugUpdateDone(" + \
            ")"
ClientMessage.DebugUpdateDone = DebugUpdateDone
class RequestDebugState(ClientMessage):
    TAG = 3
    def __init__(self):
        pass
    @staticmethod
    def read_from(stream):
        return RequestDebugState()
    def write_to(self, stream):
        stream.write_int(self.TAG)
    def __repr__(self):
        return "RequestDebugState(" + \
            ")"
ClientMessage.RequestDebugState = RequestDebugState
