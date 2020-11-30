use super::*;
#[derive(Clone, Debug, trans::Trans)]
pub enum DebugCommand {
    Add {
        data: DebugData,
    },
    Clear {
    },
}
