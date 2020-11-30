#nowarn "0058"
namespace Aicup2020.Model

type ClientMessageDebugMessage = {
    Command: DebugCommand;
    } with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write 0
        this.Command.writeTo writer
    static member readFrom(reader: System.IO.BinaryReader) = {
        Command = DebugCommand.readFrom reader
    }

type ClientMessageActionMessage = {
    Action: Action;
    } with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write 1
        this.Action.writeTo writer
    static member readFrom(reader: System.IO.BinaryReader) = {
        Action = Action.readFrom reader
    }

type ClientMessageDebugUpdateDone = struct end with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write 2
    static member readFrom(reader: System.IO.BinaryReader) = new ClientMessageDebugUpdateDone()

type ClientMessageRequestDebugState = struct end with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write 3
    static member readFrom(reader: System.IO.BinaryReader) = new ClientMessageRequestDebugState()
type ClientMessage = 
    | DebugMessage of ClientMessageDebugMessage
    | ActionMessage of ClientMessageActionMessage
    | DebugUpdateDone of ClientMessageDebugUpdateDone
    | RequestDebugState of ClientMessageRequestDebugState
    with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        match this with
            | DebugMessage value -> value.writeTo writer
            | ActionMessage value -> value.writeTo writer
            | DebugUpdateDone value -> value.writeTo writer
            | RequestDebugState value -> value.writeTo writer
    static member readFrom(reader: System.IO.BinaryReader) =
        match reader.ReadInt32() with
            | 0 -> DebugMessage (ClientMessageDebugMessage.readFrom reader)
            | 1 -> ActionMessage (ClientMessageActionMessage.readFrom reader)
            | 2 -> DebugUpdateDone (ClientMessageDebugUpdateDone.readFrom reader)
            | 3 -> RequestDebugState (ClientMessageRequestDebugState.readFrom reader)
            | x -> failwith (sprintf "Unexpected tag %d" x)
