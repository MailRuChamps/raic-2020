namespace Aicup2020.Model
{
    public abstract class DebugData
    {
        public abstract void WriteTo(System.IO.BinaryWriter writer);
        public static DebugData ReadFrom(System.IO.BinaryReader reader)
        {
            switch (reader.ReadInt32())
            {
                case Log.TAG:
                    return Log.ReadFrom(reader);
                case Primitives.TAG:
                    return Primitives.ReadFrom(reader);
                case PlacedText.TAG:
                    return PlacedText.ReadFrom(reader);
                default:
                    throw new System.Exception("Unexpected tag value");
            }
        }

        public class Log : DebugData
        {
            public const int TAG = 0;
            public string Text { get; set; }
            public Log() {}
            public Log(string text)
            {
                this.Text = text;
            }
            public static new Log ReadFrom(System.IO.BinaryReader reader)
            {
                var result = new Log();
                result.Text = System.Text.Encoding.UTF8.GetString(reader.ReadBytes(reader.ReadInt32()));
                return result;
            }
            public override void WriteTo(System.IO.BinaryWriter writer)
            {
                writer.Write(TAG);
                var TextData = System.Text.Encoding.UTF8.GetBytes(Text);
                writer.Write(TextData.Length);
                writer.Write(TextData);
            }
        }

        public class Primitives : DebugData
        {
            public const int TAG = 1;
            public Model.ColoredVertex[] Vertices { get; set; }
            public Model.PrimitiveType PrimitiveType { get; set; }
            public Primitives() {}
            public Primitives(Model.ColoredVertex[] vertices, Model.PrimitiveType primitiveType)
            {
                this.Vertices = vertices;
                this.PrimitiveType = primitiveType;
            }
            public static new Primitives ReadFrom(System.IO.BinaryReader reader)
            {
                var result = new Primitives();
                result.Vertices = new Model.ColoredVertex[reader.ReadInt32()];
                for (int i = 0; i < result.Vertices.Length; i++)
                {
                    result.Vertices[i] = Model.ColoredVertex.ReadFrom(reader);
                }
                switch (reader.ReadInt32())
                {
                case 0:
                    result.PrimitiveType = Model.PrimitiveType.Lines;
                    break;
                case 1:
                    result.PrimitiveType = Model.PrimitiveType.Triangles;
                    break;
                default:
                    throw new System.Exception("Unexpected tag value");
                }
                return result;
            }
            public override void WriteTo(System.IO.BinaryWriter writer)
            {
                writer.Write(TAG);
                writer.Write(Vertices.Length);
                foreach (var VerticesElement in Vertices)
                {
                    VerticesElement.WriteTo(writer);
                }
                writer.Write((int) (PrimitiveType));
            }
        }

        public class PlacedText : DebugData
        {
            public const int TAG = 2;
            public Model.ColoredVertex Vertex { get; set; }
            public string Text { get; set; }
            public float Alignment { get; set; }
            public float Size { get; set; }
            public PlacedText() {}
            public PlacedText(Model.ColoredVertex vertex, string text, float alignment, float size)
            {
                this.Vertex = vertex;
                this.Text = text;
                this.Alignment = alignment;
                this.Size = size;
            }
            public static new PlacedText ReadFrom(System.IO.BinaryReader reader)
            {
                var result = new PlacedText();
                result.Vertex = Model.ColoredVertex.ReadFrom(reader);
                result.Text = System.Text.Encoding.UTF8.GetString(reader.ReadBytes(reader.ReadInt32()));
                result.Alignment = reader.ReadSingle();
                result.Size = reader.ReadSingle();
                return result;
            }
            public override void WriteTo(System.IO.BinaryWriter writer)
            {
                writer.Write(TAG);
                Vertex.WriteTo(writer);
                var TextData = System.Text.Encoding.UTF8.GetBytes(Text);
                writer.Write(TextData.Length);
                writer.Write(TextData);
                writer.Write(Alignment);
                writer.Write(Size);
            }
        }
    }
}
