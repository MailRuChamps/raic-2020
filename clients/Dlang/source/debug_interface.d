import model;
import stream;

class DebugInterface
{
    this(Stream stream)
    {
        this.stream = stream;
    }

    void send(const DebugCommand command)
    {
        // TODO: Construct actual message, this is a hack :)
        stream.write(ClientMessage.DebugMessage.TAG);
        command.writeTo(stream);
        stream.flush();
    }

    DebugState getState()
    {
        new ClientMessage.RequestDebugState().writeTo(stream);
        stream.flush();
        return DebugState.readFrom(stream);
    }

private:
    Stream stream;
}
