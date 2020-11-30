#nowarn "0058"
namespace Aicup2020.Model
type MoveAction = {
    Target: Vec2Int;
    FindClosestPosition: bool;
    BreakThrough: bool;
    } with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        this.Target.writeTo writer
        writer.Write this.FindClosestPosition
        writer.Write this.BreakThrough
    static member readFrom(reader: System.IO.BinaryReader) = {
        Target = Vec2Int.readFrom reader
        FindClosestPosition = reader.ReadBoolean()
        BreakThrough = reader.ReadBoolean()
    }
