#nowarn "0058"
namespace Aicup2020.Model
type AttackAction = {
    Target: option<int>;
    AutoAttack: option<AutoAttack>;
    } with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        match this.Target with
            | Some value ->
                writer.Write true
                writer.Write value
            | None -> writer.Write false
        match this.AutoAttack with
            | Some value ->
                writer.Write true
                value.writeTo writer
            | None -> writer.Write false
    static member readFrom(reader: System.IO.BinaryReader) = {
        Target = match reader.ReadBoolean() with
            | true ->
                Some(
                    reader.ReadInt32()
                    )
            | false -> None
        AutoAttack = match reader.ReadBoolean() with
            | true ->
                Some(
                    AutoAttack.readFrom reader
                    )
            | false -> None
    }
