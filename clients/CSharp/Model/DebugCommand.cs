namespace Aicup2020.Model
{
    public abstract class DebugCommand
    {
        public abstract void WriteTo(System.IO.BinaryWriter writer);
        public static DebugCommand ReadFrom(System.IO.BinaryReader reader)
        {
            switch (reader.ReadInt32())
            {
                case Add.TAG:
                    return Add.ReadFrom(reader);
                case Clear.TAG:
                    return Clear.ReadFrom(reader);
                default:
                    throw new System.Exception("Unexpected tag value");
            }
        }

        public class Add : DebugCommand
        {
            public const int TAG = 0;
            public Model.DebugData Data { get; set; }
            public Add() {}
            public Add(Model.DebugData data)
            {
                this.Data = data;
            }
            public static new Add ReadFrom(System.IO.BinaryReader reader)
            {
                var result = new Add();
                result.Data = Model.DebugData.ReadFrom(reader);
                return result;
            }
            public override void WriteTo(System.IO.BinaryWriter writer)
            {
                writer.Write(TAG);
                Data.WriteTo(writer);
            }
        }

        public class Clear : DebugCommand
        {
            public const int TAG = 1;
            public Clear() {}
            public static new Clear ReadFrom(System.IO.BinaryReader reader)
            {
                var result = new Clear();
                return result;
            }
            public override void WriteTo(System.IO.BinaryWriter writer)
            {
                writer.Write(TAG);
            }
        }
    }
}
