use super::*;
#[derive(Clone, Debug, trans::Trans)]
pub struct AutoAttack {
    pub pathfind_range: i32,
    pub valid_targets: Vec<EntityType>,
}
