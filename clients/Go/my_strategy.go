package main

import (
	. "aicup2020/model"
)

type MyStrategy struct{}

func NewMyStrategy() MyStrategy {
	return MyStrategy{}
}

func (strategy MyStrategy) getAction(playerView PlayerView, debugInterface *DebugInterface) Action {
	return Action{
		EntityActions: make(map[int32]EntityAction),
	}
}

func (strategy MyStrategy) debugUpdate(playerView PlayerView, debugInterface DebugInterface) {
	debugInterface.Send(DebugCommandClear{})
	debugInterface.GetState()
}
