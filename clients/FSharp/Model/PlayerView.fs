#nowarn "0058"
namespace Aicup2020.Model
type PlayerView = {
    MyId: int;
    MapSize: int;
    FogOfWar: bool;
    EntityProperties: Map<EntityType, EntityProperties>;
    MaxTickCount: int;
    MaxPathfindNodes: int;
    CurrentTick: int;
    Players: Player[];
    Entities: Entity[];
    } with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write this.MyId
        writer.Write this.MapSize
        writer.Write this.FogOfWar
        writer.Write this.EntityProperties.Count
        this.EntityProperties |> Map.iter (fun key value ->
            writer.Write (int key)
            value.writeTo writer
        )
        writer.Write this.MaxTickCount
        writer.Write this.MaxPathfindNodes
        writer.Write this.CurrentTick
        writer.Write this.Players.Length
        this.Players |> Array.iter (fun value ->
            value.writeTo writer
        )
        writer.Write this.Entities.Length
        this.Entities |> Array.iter (fun value ->
            value.writeTo writer
        )
    static member readFrom(reader: System.IO.BinaryReader) = {
        MyId = reader.ReadInt32()
        MapSize = reader.ReadInt32()
        FogOfWar = reader.ReadBoolean()
        EntityProperties = [for _ in 1 .. reader.ReadInt32() do
            let key = reader.ReadInt32() |> enum
            let value = EntityProperties.readFrom reader
            yield (key, value)
            ] |> Map.ofList
        MaxTickCount = reader.ReadInt32()
        MaxPathfindNodes = reader.ReadInt32()
        CurrentTick = reader.ReadInt32()
        Players = [|for _ in 1 .. reader.ReadInt32() do
            yield Player.readFrom reader
        |]
        Entities = [|for _ in 1 .. reader.ReadInt32() do
            yield Entity.readFrom reader
        |]
    }
