use super::*;
#[derive(Clone, Debug, PartialEq, Eq, Hash, trans::Trans)]
pub struct AttackProperties {
    pub attack_range: i32,
    pub damage: i32,
    pub collect_resource: bool,
}
