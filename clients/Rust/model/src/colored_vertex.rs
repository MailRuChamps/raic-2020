use super::*;
#[derive(Clone, Debug, trans::Trans)]
pub struct ColoredVertex {
    pub world_pos: Option<Vec2F32>,
    pub screen_offset: Vec2F32,
    pub color: Color,
}
