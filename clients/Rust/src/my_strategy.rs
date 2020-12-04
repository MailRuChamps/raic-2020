use super::DebugInterface;
use model::*;

pub struct MyStrategy {}

impl MyStrategy {
    pub fn new() -> Self {
        Self {}
    }
    pub fn get_action(
        &mut self,
        player_view: &PlayerView,
        debug_interface: Option<&mut DebugInterface>,
    ) -> Action {
        let mut result = Action {
            entity_actions: std::collections::HashMap::new(),
        };
        let my_id = player_view.my_id;
        for entity in player_view
            .entities
            .iter()
            .filter(|entity| entity.player_id == Some(my_id))
        {
            let properties = &player_view.entity_properties[&entity.entity_type];

            let mut move_action = None;
            let mut build_action = None;
            if properties.can_move {
                move_action = Some(MoveAction {
                    target: Vec2I32 {
                        x: player_view.map_size - 1,
                        y: player_view.map_size - 1,
                    },
                    find_closest_position: true,
                    break_through: true,
                });
            } else if let Some(build_properties) = &properties.build {
                let entity_type = &build_properties.options[0];
                let current_units = player_view
                    .entities
                    .iter()
                    .filter(|entity| {
                        entity.player_id == Some(my_id) && entity.entity_type == *entity_type
                    })
                    .count() as i32;
                if (current_units + 1) * player_view.entity_properties[&entity_type].population_use
                    <= properties.population_provide
                {
                    build_action = Some(BuildAction {
                        entity_type: entity_type.clone(),
                        position: Vec2I32 {
                            x: entity.position.x + properties.size,
                            y: entity.position.y + properties.size - 1,
                        },
                    });
                }
            }
            result.entity_actions.insert(
                entity.id,
                EntityAction {
                    move_action,
                    build_action,
                    attack_action: Some(AttackAction {
                        target: None,
                        auto_attack: Some(AutoAttack {
                            pathfind_range: properties.sight_range,
                            valid_targets: if entity.entity_type == EntityType::BuilderUnit {
                                vec![EntityType::Resource]
                            } else {
                                vec![]
                            },
                        }),
                    }),
                    repair_action: None,
                },
            );
        }
        result
    }
    pub fn debug_update(&mut self, player_view: &PlayerView, debug_interface: &mut DebugInterface) {
        debug_interface.send(DebugCommand::Clear {});
        debug_interface.get_state();
    }
}
