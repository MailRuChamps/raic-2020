namespace Aicup2020.Model
{
    public struct DebugState
    {
        public Model.Vec2Int WindowSize { get; set; }
        public Model.Vec2Float MousePosWindow { get; set; }
        public Model.Vec2Float MousePosWorld { get; set; }
        public string[] PressedKeys { get; set; }
        public Model.Camera Camera { get; set; }
        public int PlayerIndex { get; set; }
        public DebugState(Model.Vec2Int windowSize, Model.Vec2Float mousePosWindow, Model.Vec2Float mousePosWorld, string[] pressedKeys, Model.Camera camera, int playerIndex)
        {
            this.WindowSize = windowSize;
            this.MousePosWindow = mousePosWindow;
            this.MousePosWorld = mousePosWorld;
            this.PressedKeys = pressedKeys;
            this.Camera = camera;
            this.PlayerIndex = playerIndex;
        }
        public static DebugState ReadFrom(System.IO.BinaryReader reader)
        {
            var result = new DebugState();
            result.WindowSize = Model.Vec2Int.ReadFrom(reader);
            result.MousePosWindow = Model.Vec2Float.ReadFrom(reader);
            result.MousePosWorld = Model.Vec2Float.ReadFrom(reader);
            result.PressedKeys = new string[reader.ReadInt32()];
            for (int i = 0; i < result.PressedKeys.Length; i++)
            {
                result.PressedKeys[i] = System.Text.Encoding.UTF8.GetString(reader.ReadBytes(reader.ReadInt32()));
            }
            result.Camera = Model.Camera.ReadFrom(reader);
            result.PlayerIndex = reader.ReadInt32();
            return result;
        }
        public void WriteTo(System.IO.BinaryWriter writer)
        {
            WindowSize.WriteTo(writer);
            MousePosWindow.WriteTo(writer);
            MousePosWorld.WriteTo(writer);
            writer.Write(PressedKeys.Length);
            foreach (var PressedKeysElement in PressedKeys)
            {
                var PressedKeysElementData = System.Text.Encoding.UTF8.GetBytes(PressedKeysElement);
                writer.Write(PressedKeysElementData.Length);
                writer.Write(PressedKeysElementData);
            }
            Camera.WriteTo(writer);
            writer.Write(PlayerIndex);
        }
    }
}
