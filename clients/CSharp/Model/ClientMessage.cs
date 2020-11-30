namespace Aicup2020.Model
{
    public abstract class ClientMessage
    {
        public abstract void WriteTo(System.IO.BinaryWriter writer);
        public static ClientMessage ReadFrom(System.IO.BinaryReader reader)
        {
            switch (reader.ReadInt32())
            {
                case DebugMessage.TAG:
                    return DebugMessage.ReadFrom(reader);
                case ActionMessage.TAG:
                    return ActionMessage.ReadFrom(reader);
                case DebugUpdateDone.TAG:
                    return DebugUpdateDone.ReadFrom(reader);
                case RequestDebugState.TAG:
                    return RequestDebugState.ReadFrom(reader);
                default:
                    throw new System.Exception("Unexpected tag value");
            }
        }

        public class DebugMessage : ClientMessage
        {
            public const int TAG = 0;
            public Model.DebugCommand Command { get; set; }
            public DebugMessage() {}
            public DebugMessage(Model.DebugCommand command)
            {
                this.Command = command;
            }
            public static new DebugMessage ReadFrom(System.IO.BinaryReader reader)
            {
                var result = new DebugMessage();
                result.Command = Model.DebugCommand.ReadFrom(reader);
                return result;
            }
            public override void WriteTo(System.IO.BinaryWriter writer)
            {
                writer.Write(TAG);
                Command.WriteTo(writer);
            }
        }

        public class ActionMessage : ClientMessage
        {
            public const int TAG = 1;
            public Model.Action Action { get; set; }
            public ActionMessage() {}
            public ActionMessage(Model.Action action)
            {
                this.Action = action;
            }
            public static new ActionMessage ReadFrom(System.IO.BinaryReader reader)
            {
                var result = new ActionMessage();
                result.Action = Model.Action.ReadFrom(reader);
                return result;
            }
            public override void WriteTo(System.IO.BinaryWriter writer)
            {
                writer.Write(TAG);
                Action.WriteTo(writer);
            }
        }

        public class DebugUpdateDone : ClientMessage
        {
            public const int TAG = 2;
            public DebugUpdateDone() {}
            public static new DebugUpdateDone ReadFrom(System.IO.BinaryReader reader)
            {
                var result = new DebugUpdateDone();
                return result;
            }
            public override void WriteTo(System.IO.BinaryWriter writer)
            {
                writer.Write(TAG);
            }
        }

        public class RequestDebugState : ClientMessage
        {
            public const int TAG = 3;
            public RequestDebugState() {}
            public static new RequestDebugState ReadFrom(System.IO.BinaryReader reader)
            {
                var result = new RequestDebugState();
                return result;
            }
            public override void WriteTo(System.IO.BinaryWriter writer)
            {
                writer.Write(TAG);
            }
        }
    }
}
