import model.*;

public class MyStrategy {
    public Action getAction(PlayerView playerView, DebugInterface debugInterface) {
        return new Action(new java.util.HashMap<>());
    }
    public void debugUpdate(PlayerView playerView, DebugInterface debugInterface) {
        debugInterface.send(new DebugCommand.Clear());
        debugInterface.getState();
    }
}