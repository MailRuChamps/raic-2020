use super::*;
#[derive(Clone, Debug, PartialEq, Eq, Hash, trans::Trans)]
pub enum EntityType {
    Wall,
    House,
    BuilderBase,
    BuilderUnit,
    MeleeBase,
    MeleeUnit,
    RangedBase,
    RangedUnit,
    Resource,
    Turret,
}
