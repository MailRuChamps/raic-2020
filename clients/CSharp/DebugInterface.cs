using System.IO;

namespace Aicup2020
{
    public class DebugInterface
    {
        private BinaryWriter writer;
        private BinaryReader reader;
        public DebugInterface(BinaryReader reader, BinaryWriter writer)
        {
            this.reader = reader;
            this.writer = writer;
        }
        public void Send(Model.DebugCommand command)
        {
            new Model.ClientMessage.DebugMessage(command).WriteTo(writer);
            writer.Flush();
        }
        public Model.DebugState GetState()
        {
            new Model.ClientMessage.RequestDebugState().WriteTo(writer);
            writer.Flush();
            return Model.DebugState.ReadFrom(reader);
        }
    }
}