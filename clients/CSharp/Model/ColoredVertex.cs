namespace Aicup2020.Model
{
    public struct ColoredVertex
    {
        public Model.Vec2Float? WorldPos { get; set; }
        public Model.Vec2Float ScreenOffset { get; set; }
        public Model.Color Color { get; set; }
        public ColoredVertex(Model.Vec2Float? worldPos, Model.Vec2Float screenOffset, Model.Color color)
        {
            this.WorldPos = worldPos;
            this.ScreenOffset = screenOffset;
            this.Color = color;
        }
        public static ColoredVertex ReadFrom(System.IO.BinaryReader reader)
        {
            var result = new ColoredVertex();
            if (reader.ReadBoolean())
            {
                result.WorldPos = Model.Vec2Float.ReadFrom(reader);
            } else
            {
                result.WorldPos = null;
            }
            result.ScreenOffset = Model.Vec2Float.ReadFrom(reader);
            result.Color = Model.Color.ReadFrom(reader);
            return result;
        }
        public void WriteTo(System.IO.BinaryWriter writer)
        {
            if (!WorldPos.HasValue)
            {
                writer.Write(false);
            } else
            {
                writer.Write(true);
                WorldPos.Value.WriteTo(writer);
            }
            ScreenOffset.WriteTo(writer);
            Color.WriteTo(writer);
        }
    }
}
