namespace Aicup2020.Model
{
    public abstract class ServerMessage
    {
        public abstract void WriteTo(System.IO.BinaryWriter writer);
        public static ServerMessage ReadFrom(System.IO.BinaryReader reader)
        {
            switch (reader.ReadInt32())
            {
                case GetAction.TAG:
                    return GetAction.ReadFrom(reader);
                case Finish.TAG:
                    return Finish.ReadFrom(reader);
                case DebugUpdate.TAG:
                    return DebugUpdate.ReadFrom(reader);
                default:
                    throw new System.Exception("Unexpected tag value");
            }
        }

        public class GetAction : ServerMessage
        {
            public const int TAG = 0;
            public Model.PlayerView PlayerView { get; set; }
            public bool DebugAvailable { get; set; }
            public GetAction() {}
            public GetAction(Model.PlayerView playerView, bool debugAvailable)
            {
                this.PlayerView = playerView;
                this.DebugAvailable = debugAvailable;
            }
            public static new GetAction ReadFrom(System.IO.BinaryReader reader)
            {
                var result = new GetAction();
                result.PlayerView = Model.PlayerView.ReadFrom(reader);
                result.DebugAvailable = reader.ReadBoolean();
                return result;
            }
            public override void WriteTo(System.IO.BinaryWriter writer)
            {
                writer.Write(TAG);
                PlayerView.WriteTo(writer);
                writer.Write(DebugAvailable);
            }
        }

        public class Finish : ServerMessage
        {
            public const int TAG = 1;
            public Finish() {}
            public static new Finish ReadFrom(System.IO.BinaryReader reader)
            {
                var result = new Finish();
                return result;
            }
            public override void WriteTo(System.IO.BinaryWriter writer)
            {
                writer.Write(TAG);
            }
        }

        public class DebugUpdate : ServerMessage
        {
            public const int TAG = 2;
            public Model.PlayerView PlayerView { get; set; }
            public DebugUpdate() {}
            public DebugUpdate(Model.PlayerView playerView)
            {
                this.PlayerView = playerView;
            }
            public static new DebugUpdate ReadFrom(System.IO.BinaryReader reader)
            {
                var result = new DebugUpdate();
                result.PlayerView = Model.PlayerView.ReadFrom(reader);
                return result;
            }
            public override void WriteTo(System.IO.BinaryWriter writer)
            {
                writer.Write(TAG);
                PlayerView.WriteTo(writer);
            }
        }
    }
}
