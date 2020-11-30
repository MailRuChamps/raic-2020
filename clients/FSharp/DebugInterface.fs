namespace Aicup2020

type DebugInterface(reader, writer) =
    member this.send(command) =
        (Model.ClientMessage.DebugMessage { Command = command }).writeTo writer
        writer.Flush()

    member this.getState(): Model.DebugState =
        (new Model.ClientMessageRequestDebugState()).writeTo writer
        writer.Flush()
        Model.DebugState.readFrom reader
