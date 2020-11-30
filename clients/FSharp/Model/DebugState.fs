#nowarn "0058"
namespace Aicup2020.Model
type DebugState = {
    WindowSize: Vec2Int;
    MousePosWindow: Vec2Single;
    MousePosWorld: Vec2Single;
    PressedKeys: string[];
    Camera: Camera;
    PlayerIndex: int;
    } with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        this.WindowSize.writeTo writer
        this.MousePosWindow.writeTo writer
        this.MousePosWorld.writeTo writer
        writer.Write this.PressedKeys.Length
        this.PressedKeys |> Array.iter (fun value ->
            let valueData : byte[] = System.Text.Encoding.UTF8.GetBytes value
            writer.Write valueData.Length
            writer.Write valueData
        )
        this.Camera.writeTo writer
        writer.Write this.PlayerIndex
    static member readFrom(reader: System.IO.BinaryReader) = {
        WindowSize = Vec2Int.readFrom reader
        MousePosWindow = Vec2Single.readFrom reader
        MousePosWorld = Vec2Single.readFrom reader
        PressedKeys = [|for _ in 1 .. reader.ReadInt32() do
            yield reader.ReadInt32() |> reader.ReadBytes |> System.Text.Encoding.UTF8.GetString
        |]
        Camera = Camera.readFrom reader
        PlayerIndex = reader.ReadInt32()
    }
