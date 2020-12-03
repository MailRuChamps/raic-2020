use super::*;
#[derive(Clone, Debug, trans::Trans)]
pub struct EntityProperties {
    pub size: i32,
    pub build_score: i32,
    pub destroy_score: i32,
    pub can_move: bool,
    pub population_provide: i32,
    pub population_use: i32,
    pub max_health: i32,
    pub initial_cost: i32,
    pub sight_range: i32,
    pub resource_per_health: i32,
    pub build: Option<BuildProperties>,
    pub attack: Option<AttackProperties>,
    pub repair: Option<RepairProperties>,
}
