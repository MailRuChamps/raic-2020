#nowarn "0058"
namespace Aicup2020.Model
type RepairProperties = {
    ValidTargets: EntityType[];
    Power: int;
    } with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write this.ValidTargets.Length
        this.ValidTargets |> Array.iter (fun value ->
            writer.Write (int value)
        )
        writer.Write this.Power
    static member readFrom(reader: System.IO.BinaryReader) = {
        ValidTargets = [|for _ in 1 .. reader.ReadInt32() do
            yield reader.ReadInt32() |> enum
        |]
        Power = reader.ReadInt32()
    }
