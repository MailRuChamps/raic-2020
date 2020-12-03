from .build_properties import BuildProperties
from .attack_properties import AttackProperties
from .repair_properties import RepairProperties
class EntityProperties:
    def __init__(self, size, build_score, destroy_score, can_move, population_provide, population_use, max_health, initial_cost, sight_range, resource_per_health, build, attack, repair):
        self.size = size
        self.build_score = build_score
        self.destroy_score = destroy_score
        self.can_move = can_move
        self.population_provide = population_provide
        self.population_use = population_use
        self.max_health = max_health
        self.initial_cost = initial_cost
        self.sight_range = sight_range
        self.resource_per_health = resource_per_health
        self.build = build
        self.attack = attack
        self.repair = repair
    @staticmethod
    def read_from(stream):
        size = stream.read_int()
        build_score = stream.read_int()
        destroy_score = stream.read_int()
        can_move = stream.read_bool()
        population_provide = stream.read_int()
        population_use = stream.read_int()
        max_health = stream.read_int()
        initial_cost = stream.read_int()
        sight_range = stream.read_int()
        resource_per_health = stream.read_int()
        if stream.read_bool():
            build = BuildProperties.read_from(stream)
        else:
            build = None
        if stream.read_bool():
            attack = AttackProperties.read_from(stream)
        else:
            attack = None
        if stream.read_bool():
            repair = RepairProperties.read_from(stream)
        else:
            repair = None
        return EntityProperties(size, build_score, destroy_score, can_move, population_provide, population_use, max_health, initial_cost, sight_range, resource_per_health, build, attack, repair)
    def write_to(self, stream):
        stream.write_int(self.size)
        stream.write_int(self.build_score)
        stream.write_int(self.destroy_score)
        stream.write_bool(self.can_move)
        stream.write_int(self.population_provide)
        stream.write_int(self.population_use)
        stream.write_int(self.max_health)
        stream.write_int(self.initial_cost)
        stream.write_int(self.sight_range)
        stream.write_int(self.resource_per_health)
        if self.build is None:
            stream.write_bool(False)
        else:
            stream.write_bool(True)
            self.build.write_to(stream)
        if self.attack is None:
            stream.write_bool(False)
        else:
            stream.write_bool(True)
            self.attack.write_to(stream)
        if self.repair is None:
            stream.write_bool(False)
        else:
            stream.write_bool(True)
            self.repair.write_to(stream)
    def __repr__(self):
        return "EntityProperties(" + \
            repr(self.size) + "," + \
            repr(self.build_score) + "," + \
            repr(self.destroy_score) + "," + \
            repr(self.can_move) + "," + \
            repr(self.population_provide) + "," + \
            repr(self.population_use) + "," + \
            repr(self.max_health) + "," + \
            repr(self.initial_cost) + "," + \
            repr(self.sight_range) + "," + \
            repr(self.resource_per_health) + "," + \
            repr(self.build) + "," + \
            repr(self.attack) + "," + \
            repr(self.repair) + \
            ")"
