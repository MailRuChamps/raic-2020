namespace Aicup2020

open Aicup2020.Model

type MyStrategy() =
    member this.getAction(playerView: PlayerView, debugInterface: Option<DebugInterface>): Action =
        { EntityActions = Map.empty }

    member this.debugUpdate(playerView: PlayerView, debugInterface: DebugInterface) =
        debugInterface.send (DebugCommand.Clear(new DebugCommandClear()))
        debugInterface.getState () |> ignore
