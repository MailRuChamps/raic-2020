use super::*;
#[derive(Clone, Debug, trans::Trans)]
pub struct Camera {
    pub center: Vec2F32,
    pub rotation: f32,
    pub attack: f32,
    pub distance: f32,
    pub perspective: bool,
}
