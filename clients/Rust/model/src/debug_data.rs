use super::*;
#[derive(Clone, Debug, trans::Trans)]
pub enum DebugData {
    Log {
        text: String,
    },
    Primitives {
        vertices: Vec<ColoredVertex>,
        primitive_type: PrimitiveType,
    },
    PlacedText {
        vertex: ColoredVertex,
        text: String,
        alignment: f32,
        size: f32,
    },
}
