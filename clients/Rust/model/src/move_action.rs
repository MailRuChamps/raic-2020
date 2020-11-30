use super::*;
#[derive(Clone, Debug, PartialEq, Eq, Hash, trans::Trans)]
pub struct MoveAction {
    pub target: Vec2I32,
    pub find_closest_position: bool,
    pub break_through: bool,
}
