#nowarn "0058"
namespace Aicup2020.Model
type EntityAction = {
    MoveAction: option<MoveAction>;
    BuildAction: option<BuildAction>;
    AttackAction: option<AttackAction>;
    RepairAction: option<RepairAction>;
    } with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        match this.MoveAction with
            | Some value ->
                writer.Write true
                value.writeTo writer
            | None -> writer.Write false
        match this.BuildAction with
            | Some value ->
                writer.Write true
                value.writeTo writer
            | None -> writer.Write false
        match this.AttackAction with
            | Some value ->
                writer.Write true
                value.writeTo writer
            | None -> writer.Write false
        match this.RepairAction with
            | Some value ->
                writer.Write true
                value.writeTo writer
            | None -> writer.Write false
    static member readFrom(reader: System.IO.BinaryReader) = {
        MoveAction = match reader.ReadBoolean() with
            | true ->
                Some(
                    MoveAction.readFrom reader
                    )
            | false -> None
        BuildAction = match reader.ReadBoolean() with
            | true ->
                Some(
                    BuildAction.readFrom reader
                    )
            | false -> None
        AttackAction = match reader.ReadBoolean() with
            | true ->
                Some(
                    AttackAction.readFrom reader
                    )
            | false -> None
        RepairAction = match reader.ReadBoolean() with
            | true ->
                Some(
                    RepairAction.readFrom reader
                    )
            | false -> None
    }
