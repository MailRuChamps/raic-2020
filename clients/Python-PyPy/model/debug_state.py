from .vec2_int import Vec2Int
from .vec2_float import Vec2Float
from .vec2_float import Vec2Float
from .camera import Camera
class DebugState:
    def __init__(self, window_size, mouse_pos_window, mouse_pos_world, pressed_keys, camera, player_index):
        self.window_size = window_size
        self.mouse_pos_window = mouse_pos_window
        self.mouse_pos_world = mouse_pos_world
        self.pressed_keys = pressed_keys
        self.camera = camera
        self.player_index = player_index
    @staticmethod
    def read_from(stream):
        window_size = Vec2Int.read_from(stream)
        mouse_pos_window = Vec2Float.read_from(stream)
        mouse_pos_world = Vec2Float.read_from(stream)
        pressed_keys = []
        for _ in range(stream.read_int()):
            pressed_keys_element = stream.read_string()
            pressed_keys.append(pressed_keys_element)
        camera = Camera.read_from(stream)
        player_index = stream.read_int()
        return DebugState(window_size, mouse_pos_window, mouse_pos_world, pressed_keys, camera, player_index)
    def write_to(self, stream):
        self.window_size.write_to(stream)
        self.mouse_pos_window.write_to(stream)
        self.mouse_pos_world.write_to(stream)
        stream.write_int(len(self.pressed_keys))
        for element in self.pressed_keys:
            stream.write_string(element)
        self.camera.write_to(stream)
        stream.write_int(self.player_index)
    def __repr__(self):
        return "DebugState(" + \
            repr(self.window_size) + "," + \
            repr(self.mouse_pos_window) + "," + \
            repr(self.mouse_pos_world) + "," + \
            repr(self.pressed_keys) + "," + \
            repr(self.camera) + "," + \
            repr(self.player_index) + \
            ")"
