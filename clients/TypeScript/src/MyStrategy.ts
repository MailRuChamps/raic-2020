import {DebugInterface} from './DebugInterface';
import * as model from './model'

export class MyStrategy {
    async getAction(playerView: model.PlayerView, debugInterface: DebugInterface | null) {
        return new model.Action(new Map());
    }

    async debugUpdate(_playerView: model.PlayerView, debugInterface: DebugInterface) {
        await debugInterface.send(new model.DebugCommand.Clear());
        await debugInterface.getState();
    }
}
