use super::*;
#[derive(Clone, Debug, trans::Trans)]
pub struct RepairProperties {
    pub valid_targets: Vec<EntityType>,
    pub power: i32,
}
