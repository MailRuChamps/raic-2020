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
type DebugCommand = 
    | Add of DebugCommandAdd
    | Clear of DebugCommandClear
    with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        match this with
            | Add value -> value.writeTo writer
            | Clear value -> value.writeTo writer
    static member readFrom(reader: System.IO.BinaryReader) =
        match reader.ReadInt32() with
            | 0 -> Add (DebugCommandAdd.readFrom reader)
            | 1 -> Clear (DebugCommandClear.readFrom reader)
            | x -> failwith (sprintf "Unexpected tag %d" x)
