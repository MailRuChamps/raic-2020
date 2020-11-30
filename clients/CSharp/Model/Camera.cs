namespace Aicup2020.Model
{
    public struct Camera
    {
        public Model.Vec2Float Center { get; set; }
        public float Rotation { get; set; }
        public float Attack { get; set; }
        public float Distance { get; set; }
        public bool Perspective { get; set; }
        public Camera(Model.Vec2Float center, float rotation, float attack, float distance, bool perspective)
        {
            this.Center = center;
            this.Rotation = rotation;
            this.Attack = attack;
            this.Distance = distance;
            this.Perspective = perspective;
        }
        public static Camera ReadFrom(System.IO.BinaryReader reader)
        {
            var result = new Camera();
            result.Center = Model.Vec2Float.ReadFrom(reader);
            result.Rotation = reader.ReadSingle();
            result.Attack = reader.ReadSingle();
            result.Distance = reader.ReadSingle();
            result.Perspective = reader.ReadBoolean();
            return result;
        }
        public void WriteTo(System.IO.BinaryWriter writer)
        {
            Center.WriteTo(writer);
            writer.Write(Rotation);
            writer.Write(Attack);
            writer.Write(Distance);
            writer.Write(Perspective);
        }
    }
}
