use super::*;
#[derive(Clone, Debug, trans::Trans)]
pub struct Entity {
    pub id: i32,
    pub player_id: Option<i32>,
    pub entity_type: EntityType,
    pub position: Vec2I32,
    pub health: i32,
    pub active: bool,
}
