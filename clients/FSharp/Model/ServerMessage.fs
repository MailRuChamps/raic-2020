#nowarn "0058"
namespace Aicup2020.Model

type ServerMessageGetAction = {
    PlayerView: PlayerView;
    DebugAvailable: bool;
    } with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write 0
        this.PlayerView.writeTo writer
        writer.Write this.DebugAvailable
    static member readFrom(reader: System.IO.BinaryReader) = {
        PlayerView = PlayerView.readFrom reader
        DebugAvailable = reader.ReadBoolean()
    }

type ServerMessageFinish = struct end with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write 1
    static member readFrom(reader: System.IO.BinaryReader) = new ServerMessageFinish()

type ServerMessageDebugUpdate = {
    PlayerView: PlayerView;
    } with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write 2
        this.PlayerView.writeTo writer
    static member readFrom(reader: System.IO.BinaryReader) = {
        PlayerView = PlayerView.readFrom reader
    }
type ServerMessage = 
    | GetAction of ServerMessageGetAction
    | Finish of ServerMessageFinish
    | DebugUpdate of ServerMessageDebugUpdate
    with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        match this with
            | GetAction value -> value.writeTo writer
            | Finish value -> value.writeTo writer
            | DebugUpdate value -> value.writeTo writer
    static member readFrom(reader: System.IO.BinaryReader) =
        match reader.ReadInt32() with
            | 0 -> GetAction (ServerMessageGetAction.readFrom reader)
            | 1 -> Finish (ServerMessageFinish.readFrom reader)
            | 2 -> DebugUpdate (ServerMessageDebugUpdate.readFrom reader)
            | x -> failwith (sprintf "Unexpected tag %d" x)
