use super::*;
#[derive(Clone, Debug, trans::Trans)]
pub struct BuildProperties {
    pub options: Vec<EntityType>,
    pub init_health: Option<i32>,
}
