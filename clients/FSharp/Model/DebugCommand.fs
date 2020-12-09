#nowarn "0058"
namespace Aicup2020.Model

type DebugCommandAdd = {
    Data: DebugData;
    } with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write 0
        this.Data.writeTo writer
    static member readFrom(reader: System.IO.BinaryReader) = {
        Data = DebugData.readFrom reader
    }

type DebugCommandClear = struct end with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write 1
    static member readFrom(reader: System.IO.BinaryReader) = new DebugCommandClear()

type DebugCommandSetAutoFlush = {
    Enable: bool;
    } with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write 2
        writer.Write this.Enable
    static member readFrom(reader: System.IO.BinaryReader) = {
        Enable = reader.ReadBoolean()
    }

type DebugCommandFlush = struct end with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write 3
    static member readFrom(reader: System.IO.BinaryReader) = new DebugCommandFlush()
type DebugCommand = 
    | Add of DebugCommandAdd
    | Clear of DebugCommandClear
    | SetAutoFlush of DebugCommandSetAutoFlush
    | Flush of DebugCommandFlush
    with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        match this with
            | Add value -> value.writeTo writer
            | Clear value -> value.writeTo writer
            | SetAutoFlush value -> value.writeTo writer
            | Flush value -> value.writeTo writer
    static member readFrom(reader: System.IO.BinaryReader) =
        match reader.ReadInt32() with
            | 0 -> Add (DebugCommandAdd.readFrom reader)
            | 1 -> Clear (DebugCommandClear.readFrom reader)
            | 2 -> SetAutoFlush (DebugCommandSetAutoFlush.readFrom reader)
            | 3 -> Flush (DebugCommandFlush.readFrom reader)
            | x -> failwith (sprintf "Unexpected tag %d" x)
