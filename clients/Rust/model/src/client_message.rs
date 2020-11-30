use super::*;
#[derive(Clone, Debug, trans::Trans)]
pub enum ClientMessage {
    DebugMessage {
        command: DebugCommand,
    },
    ActionMessage {
        action: Action,
    },
    DebugUpdateDone {
    },
    RequestDebugState {
    },
}
