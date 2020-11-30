class AttackProperties:
    def __init__(self, attack_range, damage, collect_resource):
        self.attack_range = attack_range
        self.damage = damage
        self.collect_resource = collect_resource
    @staticmethod
    def read_from(stream):
        attack_range = stream.read_int()
        damage = stream.read_int()
        collect_resource = stream.read_bool()
        return AttackProperties(attack_range, damage, collect_resource)
    def write_to(self, stream):
        stream.write_int(self.attack_range)
        stream.write_int(self.damage)
        stream.write_bool(self.collect_resource)
    def __repr__(self):
        return "AttackProperties(" + \
            repr(self.attack_range) + "," + \
            repr(self.damage) + "," + \
            repr(self.collect_resource) + \
            ")"
