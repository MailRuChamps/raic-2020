use super::*;
#[derive(Clone, Debug, PartialEq, Eq, trans::Trans)]
pub struct AttackAction {
    pub target: Option<i32>,
    pub auto_attack: Option<AutoAttack>,
}
