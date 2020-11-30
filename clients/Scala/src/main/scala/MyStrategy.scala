class MyStrategy {
  def getAction(playerView: model.PlayerView, debugInterface: Option[DebugInterface]): model.Action = model.Action(Map())
  def debugUpdate(playerView: model.PlayerView, debugInterface: DebugInterface) {
    debugInterface.send(model.DebugCommand.Clear())
    debugInterface.getState()
  }
}