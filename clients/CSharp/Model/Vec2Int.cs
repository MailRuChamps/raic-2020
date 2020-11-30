namespace Aicup2020.Model
{
    public struct Vec2Int
    {
        public int X { get; set; }
        public int Y { get; set; }
        public Vec2Int(int x, int y)
        {
            this.X = x;
            this.Y = y;
        }
        public static Vec2Int ReadFrom(System.IO.BinaryReader reader)
        {
            var result = new Vec2Int();
            result.X = reader.ReadInt32();
            result.Y = reader.ReadInt32();
            return result;
        }
        public void WriteTo(System.IO.BinaryWriter writer)
        {
            writer.Write(X);
            writer.Write(Y);
        }
    }
}
