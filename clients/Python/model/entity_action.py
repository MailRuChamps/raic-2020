from .move_action import MoveAction
from .build_action import BuildAction
from .attack_action import AttackAction
from .repair_action import RepairAction
class EntityAction:
    def __init__(self, move_action, build_action, attack_action, repair_action):
        self.move_action = move_action
        self.build_action = build_action
        self.attack_action = attack_action
        self.repair_action = repair_action
    @staticmethod
    def read_from(stream):
        if stream.read_bool():
            move_action = MoveAction.read_from(stream)
        else:
            move_action = None
        if stream.read_bool():
            build_action = BuildAction.read_from(stream)
        else:
            build_action = None
        if stream.read_bool():
            attack_action = AttackAction.read_from(stream)
        else:
            attack_action = None
        if stream.read_bool():
            repair_action = RepairAction.read_from(stream)
        else:
            repair_action = None
        return EntityAction(move_action, build_action, attack_action, repair_action)
    def write_to(self, stream):
        if self.move_action is None:
            stream.write_bool(False)
        else:
            stream.write_bool(True)
            self.move_action.write_to(stream)
        if self.build_action is None:
            stream.write_bool(False)
        else:
            stream.write_bool(True)
            self.build_action.write_to(stream)
        if self.attack_action is None:
            stream.write_bool(False)
        else:
            stream.write_bool(True)
            self.attack_action.write_to(stream)
        if self.repair_action is None:
            stream.write_bool(False)
        else:
            stream.write_bool(True)
            self.repair_action.write_to(stream)
    def __repr__(self):
        return "EntityAction(" + \
            repr(self.move_action) + "," + \
            repr(self.build_action) + "," + \
            repr(self.attack_action) + "," + \
            repr(self.repair_action) + \
            ")"
