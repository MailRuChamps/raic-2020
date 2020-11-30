use super::*;
#[derive(Clone, Debug, trans::Trans)]
pub struct EntityAction {
    pub move_action: Option<MoveAction>,
    pub build_action: Option<BuildAction>,
    pub attack_action: Option<AttackAction>,
    pub repair_action: Option<RepairAction>,
}
