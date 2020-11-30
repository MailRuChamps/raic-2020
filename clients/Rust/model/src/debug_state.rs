use super::*;
#[derive(Clone, Debug, trans::Trans)]
pub struct DebugState {
    pub window_size: Vec2I32,
    pub mouse_pos_window: Vec2F32,
    pub mouse_pos_world: Vec2F32,
    pub pressed_keys: Vec<String>,
    pub camera: Camera,
    pub player_index: i32,
}
