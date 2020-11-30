use super::*;
#[derive(Clone, Debug, PartialEq, Eq, Hash, trans::Trans)]
pub struct RepairAction {
    pub target: i32,
}
