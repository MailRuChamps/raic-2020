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
                case SetAutoFlush.TAG:
                    return SetAutoFlush.ReadFrom(reader);
                case Flush.TAG:
                    return Flush.ReadFrom(reader);
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

        public class SetAutoFlush : DebugCommand
        {
            public const int TAG = 2;
            public bool Enable { get; set; }
            public SetAutoFlush() {}
            public SetAutoFlush(bool enable)
            {
                this.Enable = enable;
            }
            public static new SetAutoFlush ReadFrom(System.IO.BinaryReader reader)
            {
                var result = new SetAutoFlush();
                result.Enable = reader.ReadBoolean();
                return result;
            }
            public override void WriteTo(System.IO.BinaryWriter writer)
            {
                writer.Write(TAG);
                writer.Write(Enable);
            }
        }

        public class Flush : DebugCommand
        {
            public const int TAG = 3;
            public Flush() {}
            public static new Flush ReadFrom(System.IO.BinaryReader reader)
            {
                var result = new Flush();
                return result;
            }
            public override void WriteTo(System.IO.BinaryWriter writer)
            {
                writer.Write(TAG);
            }
        }
    }
}
