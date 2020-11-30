require_relative 'vec2_int'
require_relative 'vec2_float'
require_relative 'vec2_float'
require_relative 'camera'
class DebugState
    attr_accessor :window_size
    attr_accessor :mouse_pos_window
    attr_accessor :mouse_pos_world
    attr_accessor :pressed_keys
    attr_accessor :camera
    attr_accessor :player_index
    def initialize(window_size, mouse_pos_window, mouse_pos_world, pressed_keys, camera, player_index)
        @window_size = window_size
        @mouse_pos_window = mouse_pos_window
        @mouse_pos_world = mouse_pos_world
        @pressed_keys = pressed_keys
        @camera = camera
        @player_index = player_index
    end
    def self.read_from(stream)
        window_size = Vec2Int.read_from(stream)
        mouse_pos_window = Vec2Float.read_from(stream)
        mouse_pos_world = Vec2Float.read_from(stream)
        pressed_keys = []
        stream.read_int().times do |_|
            pressed_keys_element = stream.read_string()
            pressed_keys.push(pressed_keys_element)
        end
        camera = Camera.read_from(stream)
        player_index = stream.read_int()
        DebugState.new(window_size, mouse_pos_window, mouse_pos_world, pressed_keys, camera, player_index)
    end
    def write_to(stream)
        @window_size.write_to(stream)
        @mouse_pos_window.write_to(stream)
        @mouse_pos_world.write_to(stream)
        stream.write_int(@pressed_keys.length())
        @pressed_keys.each do |element|
            stream.write_string(element)
        end
        @camera.write_to(stream)
        stream.write_int(@player_index)
    end
end
