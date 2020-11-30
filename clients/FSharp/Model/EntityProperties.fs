#nowarn "0058"
namespace Aicup2020.Model
type EntityProperties = {
    Size: int;
    BuildScore: int;
    DestroyScore: int;
    CanMove: bool;
    PopulationProvide: int;
    PopulationUse: int;
    MaxHealth: int;
    Cost: int;
    SightRange: int;
    ResourcePerHealth: int;
    Build: option<BuildProperties>;
    Attack: option<AttackProperties>;
    Repair: option<RepairProperties>;
    } with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write this.Size
        writer.Write this.BuildScore
        writer.Write this.DestroyScore
        writer.Write this.CanMove
        writer.Write this.PopulationProvide
        writer.Write this.PopulationUse
        writer.Write this.MaxHealth
        writer.Write this.Cost
        writer.Write this.SightRange
        writer.Write this.ResourcePerHealth
        match this.Build with
            | Some value ->
                writer.Write true
                value.writeTo writer
            | None -> writer.Write false
        match this.Attack with
            | Some value ->
                writer.Write true
                value.writeTo writer
            | None -> writer.Write false
        match this.Repair with
            | Some value ->
                writer.Write true
                value.writeTo writer
            | None -> writer.Write false
    static member readFrom(reader: System.IO.BinaryReader) = {
        Size = reader.ReadInt32()
        BuildScore = reader.ReadInt32()
        DestroyScore = reader.ReadInt32()
        CanMove = reader.ReadBoolean()
        PopulationProvide = reader.ReadInt32()
        PopulationUse = reader.ReadInt32()
        MaxHealth = reader.ReadInt32()
        Cost = reader.ReadInt32()
        SightRange = reader.ReadInt32()
        ResourcePerHealth = reader.ReadInt32()
        Build = match reader.ReadBoolean() with
            | true ->
                Some(
                    BuildProperties.readFrom reader
                    )
            | false -> None
        Attack = match reader.ReadBoolean() with
            | true ->
                Some(
                    AttackProperties.readFrom reader
                    )
            | false -> None
        Repair = match reader.ReadBoolean() with
            | true ->
                Some(
                    RepairProperties.readFrom reader
                    )
            | false -> None
    }
