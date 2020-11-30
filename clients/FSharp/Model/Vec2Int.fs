#nowarn "0058"
namespace Aicup2020.Model
type Vec2Int = {
    X: int;
    Y: int;
    } with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write this.X
        writer.Write this.Y
    static member readFrom(reader: System.IO.BinaryReader) = {
        X = reader.ReadInt32()
        Y = reader.ReadInt32()
    }
