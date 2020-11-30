#nowarn "0058"
namespace Aicup2020.Model
type Color = {
    R: single;
    G: single;
    B: single;
    A: single;
    } with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write this.R
        writer.Write this.G
        writer.Write this.B
        writer.Write this.A
    static member readFrom(reader: System.IO.BinaryReader) = {
        R = reader.ReadSingle()
        G = reader.ReadSingle()
        B = reader.ReadSingle()
        A = reader.ReadSingle()
    }
