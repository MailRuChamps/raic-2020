#nowarn "0058"
namespace Aicup2020.Model
type Camera = {
    Center: Vec2Single;
    Rotation: single;
    Attack: single;
    Distance: single;
    Perspective: bool;
    } with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        this.Center.writeTo writer
        writer.Write this.Rotation
        writer.Write this.Attack
        writer.Write this.Distance
        writer.Write this.Perspective
    static member readFrom(reader: System.IO.BinaryReader) = {
        Center = Vec2Single.readFrom reader
        Rotation = reader.ReadSingle()
        Attack = reader.ReadSingle()
        Distance = reader.ReadSingle()
        Perspective = reader.ReadBoolean()
    }
