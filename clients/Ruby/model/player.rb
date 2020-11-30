class Player
    attr_accessor :id
    attr_accessor :score
    attr_accessor :resource
    def initialize(id, score, resource)
        @id = id
        @score = score
        @resource = resource
    end
    def self.read_from(stream)
        id = stream.read_int()
        score = stream.read_int()
        resource = stream.read_int()
        Player.new(id, score, resource)
    end
    def write_to(stream)
        stream.write_int(@id)
        stream.write_int(@score)
        stream.write_int(@resource)
    end
end
