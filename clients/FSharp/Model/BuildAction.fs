#nowarn "0058"
namespace Aicup2020.Model
type BuildAction = {
    EntityType: EntityType;
    Position: Vec2Int;
    } with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write (int this.EntityType)
        this.Position.writeTo writer
    static member readFrom(reader: System.IO.BinaryReader) = {
        EntityType = reader.ReadInt32() |> enum
        Position = Vec2Int.readFrom reader
    }
