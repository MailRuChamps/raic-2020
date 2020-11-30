#nowarn "0058"
namespace Aicup2020.Model

type DebugDataLog = {
    Text: string;
    } with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write 0
        let TextData : byte[] = System.Text.Encoding.UTF8.GetBytes this.Text
        writer.Write TextData.Length
        writer.Write TextData
    static member readFrom(reader: System.IO.BinaryReader) = {
        Text = reader.ReadInt32() |> reader.ReadBytes |> System.Text.Encoding.UTF8.GetString
    }

type DebugDataPrimitives = {
    Vertices: ColoredVertex[];
    PrimitiveType: PrimitiveType;
    } with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write 1
        writer.Write this.Vertices.Length
        this.Vertices |> Array.iter (fun value ->
            value.writeTo writer
        )
        writer.Write (int this.PrimitiveType)
    static member readFrom(reader: System.IO.BinaryReader) = {
        Vertices = [|for _ in 1 .. reader.ReadInt32() do
            yield ColoredVertex.readFrom reader
        |]
        PrimitiveType = reader.ReadInt32() |> enum
    }

type DebugDataPlacedText = {
    Vertex: ColoredVertex;
    Text: string;
    Alignment: single;
    Size: single;
    } with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        writer.Write 2
        this.Vertex.writeTo writer
        let TextData : byte[] = System.Text.Encoding.UTF8.GetBytes this.Text
        writer.Write TextData.Length
        writer.Write TextData
        writer.Write this.Alignment
        writer.Write this.Size
    static member readFrom(reader: System.IO.BinaryReader) = {
        Vertex = ColoredVertex.readFrom reader
        Text = reader.ReadInt32() |> reader.ReadBytes |> System.Text.Encoding.UTF8.GetString
        Alignment = reader.ReadSingle()
        Size = reader.ReadSingle()
    }
type DebugData = 
    | Log of DebugDataLog
    | Primitives of DebugDataPrimitives
    | PlacedText of DebugDataPlacedText
    with
    member this.writeTo(writer: System.IO.BinaryWriter) =
        match this with
            | Log value -> value.writeTo writer
            | Primitives value -> value.writeTo writer
            | PlacedText value -> value.writeTo writer
    static member readFrom(reader: System.IO.BinaryReader) =
        match reader.ReadInt32() with
            | 0 -> Log (DebugDataLog.readFrom reader)
            | 1 -> Primitives (DebugDataPrimitives.readFrom reader)
            | 2 -> PlacedText (DebugDataPlacedText.readFrom reader)
            | x -> failwith (sprintf "Unexpected tag %d" x)
