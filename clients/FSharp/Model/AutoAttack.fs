#nowarn "0058"
namespace Aicup2020.Model
type AutoAttack = {
    PathfindRange: int;
    ValidTargets: EntityType[];
    } with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write this.PathfindRange
        writer.Write this.ValidTargets.Length
        this.ValidTargets |> Array.iter (fun value ->
            writer.Write (int value)
        )
    static member readFrom(reader: System.IO.BinaryReader) = {
        PathfindRange = reader.ReadInt32()
        ValidTargets = [|for _ in 1 .. reader.ReadInt32() do
            yield reader.ReadInt32() |> enum
        |]
    }
