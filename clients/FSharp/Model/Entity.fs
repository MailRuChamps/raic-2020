#nowarn "0058"
namespace Aicup2020.Model
type Entity = {
    Id: int;
    PlayerId: option<int>;
    EntityType: EntityType;
    Position: Vec2Int;
    Health: int;
    Active: bool;
    } with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write this.Id
        match this.PlayerId with
            | Some value ->
                writer.Write true
                writer.Write value
            | None -> writer.Write false
        writer.Write (int this.EntityType)
        this.Position.writeTo writer
        writer.Write this.Health
        writer.Write this.Active
    static member readFrom(reader: System.IO.BinaryReader) = {
        Id = reader.ReadInt32()
        PlayerId = match reader.ReadBoolean() with
            | true ->
                Some(
                    reader.ReadInt32()
                    )
            | false -> None
        EntityType = reader.ReadInt32() |> enum
        Position = Vec2Int.readFrom reader
        Health = reader.ReadInt32()
        Active = reader.ReadBoolean()
    }
