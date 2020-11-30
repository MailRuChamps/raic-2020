use super::DebugInterface;

pub struct MyStrategy {}

impl MyStrategy {
    pub fn new() -> Self {
        Self {}
    }
    pub fn get_action(
        &mut self,
        player_view: &model::PlayerView,
        debug_interface: Option<&mut DebugInterface>,
    ) -> model::Action {
        model::Action {
            entity_actions: std::collections::HashMap::new(),
        }
    }
    pub fn debug_update(
        &mut self,
        player_view: &model::PlayerView,
        debug_interface: &mut DebugInterface,
    ) {
        debug_interface.send(model::DebugCommand::Clear {});
        debug_interface.get_state();
    }
}
