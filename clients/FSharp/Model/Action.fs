#nowarn "0058"
namespace Aicup2020.Model
type Action = {
    EntityActions: Map<int, EntityAction>;
    } with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write this.EntityActions.Count
        this.EntityActions |> Map.iter (fun key value ->
            writer.Write key
            value.writeTo writer
        )
    static member readFrom(reader: System.IO.BinaryReader) = {
        EntityActions = [for _ in 1 .. reader.ReadInt32() do
            let key = reader.ReadInt32()
            let value = EntityAction.readFrom reader
            yield (key, value)
            ] |> Map.ofList
    }
