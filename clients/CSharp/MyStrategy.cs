using Aicup2020.Model;

namespace Aicup2020
{
    public class MyStrategy
    {
        public Action GetAction(PlayerView playerView, DebugInterface debugInterface)
        {
            return new Action(new System.Collections.Generic.Dictionary<int, Model.EntityAction>(0));
        }

        public void DebugUpdate(PlayerView playerView, DebugInterface debugInterface) 
        {
            debugInterface.Send(new DebugCommand.Clear());
            debugInterface.GetState();
        }
    }
}