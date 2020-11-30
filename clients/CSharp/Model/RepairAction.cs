namespace Aicup2020.Model
{
    public struct RepairAction
    {
        public int Target { get; set; }
        public RepairAction(int target)
        {
            this.Target = target;
        }
        public static RepairAction ReadFrom(System.IO.BinaryReader reader)
        {
            var result = new RepairAction();
            result.Target = reader.ReadInt32();
            return result;
        }
        public void WriteTo(System.IO.BinaryWriter writer)
        {
            writer.Write(Target);
        }
    }
}
