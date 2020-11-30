#nowarn "0058"
namespace Aicup2020.Model
type ColoredVertex = {
    WorldPos: option<Vec2Single>;
    ScreenOffset: Vec2Single;
    Color: Color;
    } with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        match this.WorldPos with
            | Some value ->
                writer.Write true
                value.writeTo writer
            | None -> writer.Write false
        this.ScreenOffset.writeTo writer
        this.Color.writeTo writer
    static member readFrom(reader: System.IO.BinaryReader) = {
        WorldPos = match reader.ReadBoolean() with
            | true ->
                Some(
                    Vec2Single.readFrom reader
                    )
            | false -> None
        ScreenOffset = Vec2Single.readFrom reader
        Color = Color.readFrom reader
    }
