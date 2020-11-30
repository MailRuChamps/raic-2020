#nowarn "0058"
namespace Aicup2020.Model
type Player = {
    Id: int;
    Score: int;
    Resource: int;
    } with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write this.Id
        writer.Write this.Score
        writer.Write this.Resource
    static member readFrom(reader: System.IO.BinaryReader) = {
        Id = reader.ReadInt32()
        Score = reader.ReadInt32()
        Resource = reader.ReadInt32()
    }
