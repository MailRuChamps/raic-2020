import model.*

class MyStrategy {
    fun getAction(playerView: PlayerView, debugInterface: DebugInterface?): Action {
        return Action(mutableMapOf())
    }
    fun debugUpdate(playerView: PlayerView, debugInterface: DebugInterface) {
        debugInterface.send(model.DebugCommand.Clear())
        debugInterface.getState()
    }
}
