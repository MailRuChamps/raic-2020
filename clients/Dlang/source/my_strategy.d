import model;
import debug_interface;
import std.typecons;
import std.conv;

class MyStrategy
{
    Action getAction(PlayerView playerView, DebugInterface debugInterface)
    {
        Action action;
        return action;
    }

    void debugUpdate(PlayerView playerView, DebugInterface debugInterface)
    {
        debugInterface.send(new DebugCommand.Clear());
        debugInterface.getState();
    }
}
