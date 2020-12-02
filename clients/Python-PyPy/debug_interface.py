import model


class DebugInterface:
    def __init__(self, reader, writer):
        self.reader = reader
        self.writer = writer

    def send(self, command):
        model.ClientMessage.DebugMessage(command).write_to(self.writer)
        self.writer.flush()

    def get_state(self):
        model.ClientMessage.RequestDebugState().write_to(self.writer)
        self.writer.flush()
        return model.DebugState.read_from(self.reader)
