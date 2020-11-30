namespace Aicup2020.Model
{
    public struct MoveAction
    {
        public Model.Vec2Int Target { get; set; }
        public bool FindClosestPosition { get; set; }
        public bool BreakThrough { get; set; }
        public MoveAction(Model.Vec2Int target, bool findClosestPosition, bool breakThrough)
        {
            this.Target = target;
            this.FindClosestPosition = findClosestPosition;
            this.BreakThrough = breakThrough;
        }
        public static MoveAction ReadFrom(System.IO.BinaryReader reader)
        {
            var result = new MoveAction();
            result.Target = Model.Vec2Int.ReadFrom(reader);
            result.FindClosestPosition = reader.ReadBoolean();
            result.BreakThrough = reader.ReadBoolean();
            return result;
        }
        public void WriteTo(System.IO.BinaryWriter writer)
        {
            Target.WriteTo(writer);
            writer.Write(FindClosestPosition);
            writer.Write(BreakThrough);
        }
    }
}
