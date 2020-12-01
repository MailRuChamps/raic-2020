use super::*;
#[derive(Clone, Debug, PartialEq, Eq, trans::Trans)]
pub struct AutoAttack {
    pub pathfind_range: i32,
    pub valid_targets: Vec<EntityType>,
}
