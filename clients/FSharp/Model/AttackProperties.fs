#nowarn "0058"
namespace Aicup2020.Model
type AttackProperties = {
    AttackRange: int;
    Damage: int;
    CollectResource: bool;
    } with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write this.AttackRange
        writer.Write this.Damage
        writer.Write this.CollectResource
    static member readFrom(reader: System.IO.BinaryReader) = {
        AttackRange = reader.ReadInt32()
        Damage = reader.ReadInt32()
        CollectResource = reader.ReadBoolean()
    }
