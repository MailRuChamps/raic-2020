#nowarn "0058"
namespace Aicup2020.Model
type BuildProperties = {
    Options: EntityType[];
    InitHealth: option<int>;
    } with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write this.Options.Length
        this.Options |> Array.iter (fun value ->
            writer.Write (int value)
        )
        match this.InitHealth with
            | Some value ->
                writer.Write true
                writer.Write value
            | None -> writer.Write false
    static member readFrom(reader: System.IO.BinaryReader) = {
        Options = [|for _ in 1 .. reader.ReadInt32() do
            yield reader.ReadInt32() |> enum
        |]
        InitHealth = match reader.ReadBoolean() with
            | true ->
                Some(
                    reader.ReadInt32()
                    )
            | false -> None
    }
