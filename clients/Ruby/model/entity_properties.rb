require_relative 'build_properties'
require_relative 'attack_properties'
require_relative 'repair_properties'
class EntityProperties
    attr_accessor :size
    attr_accessor :build_score
    attr_accessor :destroy_score
    attr_accessor :can_move
    attr_accessor :population_provide
    attr_accessor :population_use
    attr_accessor :max_health
    attr_accessor :cost
    attr_accessor :sight_range
    attr_accessor :resource_per_health
    attr_accessor :build
    attr_accessor :attack
    attr_accessor :repair
    def initialize(size, build_score, destroy_score, can_move, population_provide, population_use, max_health, cost, sight_range, resource_per_health, build, attack, repair)
        @size = size
        @build_score = build_score
        @destroy_score = destroy_score
        @can_move = can_move
        @population_provide = population_provide
        @population_use = population_use
        @max_health = max_health
        @cost = cost
        @sight_range = sight_range
        @resource_per_health = resource_per_health
        @build = build
        @attack = attack
        @repair = repair
    end
    def self.read_from(stream)
        size = stream.read_int()
        build_score = stream.read_int()
        destroy_score = stream.read_int()
        can_move = stream.read_bool()
        population_provide = stream.read_int()
        population_use = stream.read_int()
        max_health = stream.read_int()
        cost = stream.read_int()
        sight_range = stream.read_int()
        resource_per_health = stream.read_int()
        if stream.read_bool()
            build = BuildProperties.read_from(stream)
        else
            build = nil
        end
        if stream.read_bool()
            attack = AttackProperties.read_from(stream)
        else
            attack = nil
        end
        if stream.read_bool()
            repair = RepairProperties.read_from(stream)
        else
            repair = nil
        end
        EntityProperties.new(size, build_score, destroy_score, can_move, population_provide, population_use, max_health, cost, sight_range, resource_per_health, build, attack, repair)
    end
    def write_to(stream)
        stream.write_int(@size)
        stream.write_int(@build_score)
        stream.write_int(@destroy_score)
        stream.write_bool(@can_move)
        stream.write_int(@population_provide)
        stream.write_int(@population_use)
        stream.write_int(@max_health)
        stream.write_int(@cost)
        stream.write_int(@sight_range)
        stream.write_int(@resource_per_health)
        if @build.nil?
            stream.write_bool(false)
        else
            stream.write_bool(true)
            @build.write_to(stream)
        end
        if @attack.nil?
            stream.write_bool(false)
        else
            stream.write_bool(true)
            @attack.write_to(stream)
        end
        if @repair.nil?
            stream.write_bool(false)
        else
            stream.write_bool(true)
            @repair.write_to(stream)
        end
    end
end
