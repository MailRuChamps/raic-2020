use super::*;
#[derive(Clone, Debug, PartialEq, Eq, Hash, trans::Trans)]
pub struct BuildAction {
    pub entity_type: EntityType,
    pub position: Vec2I32,
}
