#nowarn "0058"
namespace Aicup2020.Model
type RepairAction = {
    Target: int;
    } with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write this.Target
    static member readFrom(reader: System.IO.BinaryReader) = {
        Target = reader.ReadInt32()
    }
