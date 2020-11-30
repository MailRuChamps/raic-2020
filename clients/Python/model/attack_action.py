from .auto_attack import AutoAttack
class AttackAction:
    def __init__(self, target, auto_attack):
        self.target = target
        self.auto_attack = auto_attack
    @staticmethod
    def read_from(stream):
        if stream.read_bool():
            target = stream.read_int()
        else:
            target = None
        if stream.read_bool():
            auto_attack = AutoAttack.read_from(stream)
        else:
            auto_attack = None
        return AttackAction(target, auto_attack)
    def write_to(self, stream):
        if self.target is None:
            stream.write_bool(False)
        else:
            stream.write_bool(True)
            stream.write_int(self.target)
        if self.auto_attack is None:
            stream.write_bool(False)
        else:
            stream.write_bool(True)
            self.auto_attack.write_to(stream)
    def __repr__(self):
        return "AttackAction(" + \
            repr(self.target) + "," + \
            repr(self.auto_attack) + \
            ")"
