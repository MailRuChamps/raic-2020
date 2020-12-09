require 'socket'
require 'stringio'
require_relative 'stream_wrapper'
require_relative 'model'
require_relative 'my_strategy'
require_relative 'debug_interface'

class SocketWrapper
    def initialize(socket)
        @socket = socket
        @read_buffer = StringIO.new('', 'a+b')
        @write_buffer = ''
    end

    def read_bytes(byte_count)
        @read_buffer = StringIO.new('', 'a+b') unless @read_buffer.is_a? StringIO

        data = @read_buffer.read(byte_count) || ''

        while data.length < byte_count
            @read_buffer = StringIO.new(@socket.recv(102_400), 'a+b')
            data << @read_buffer.read(byte_count - data.length)
        end

        data
    end

    def write_bytes(data)
        @write_buffer << data
    end

    def flush
        @socket.write(@write_buffer)
        @write_buffer = ''
        @read_buffer = ''
        @socket.flush
    end
end

class Runner
    def initialize(host, port, token)
        socket = TCPSocket.open(host, port)
        socket.setsockopt(Socket::IPPROTO_TCP, Socket::TCP_NODELAY, 1)
        stream = SocketWrapper.new(socket)
        @reader = StreamWrapper.new(stream)
        @writer = StreamWrapper.new(stream)
        @token = token
        @writer.write_string(@token)
        @writer.flush()
    end

    def run()
        strategy = MyStrategy.new()
        debug_interface = DebugInterface.new(@reader, @writer)

        while true
            message = ServerMessage.read_from(@reader)
            if message.instance_of? ServerMessage::GetAction
                ClientMessage::ActionMessage.new(strategy.get_action(message.player_view, message.debug_available ? debug_interface : nil)).write_to(@writer)
                @writer.flush()
            elsif message.instance_of? ServerMessage::Finish
                break
            elsif message.instance_of? ServerMessage::DebugUpdate
                strategy.debug_update(message.player_view, debug_interface)
                ClientMessage::DebugUpdateDone.new().write_to(@writer)
                @writer.flush()
            else
                raise "Unexpected server message"
            end
        end
    end
end

host = ARGV.length < 1 ? "127.0.0.1" : ARGV[0]
port = ARGV.length < 2 ? 31001 : ARGV[1].to_i
token = ARGV.length < 3 ? "0000000000000000" : ARGV[2]
Runner.new(host, port, token).run()
