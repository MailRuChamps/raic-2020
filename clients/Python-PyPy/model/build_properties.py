from .entity_type import EntityType
class BuildProperties:
    def __init__(self, options, init_health):
        self.options = options
        self.init_health = init_health
    @staticmethod
    def read_from(stream):
        options = []
        for _ in range(stream.read_int()):
            options_element = EntityType(stream.read_int())
            options.append(options_element)
        if stream.read_bool():
            init_health = stream.read_int()
        else:
            init_health = None
        return BuildProperties(options, init_health)
    def write_to(self, stream):
        stream.write_int(len(self.options))
        for element in self.options:
            stream.write_int(element)
        if self.init_health is None:
            stream.write_bool(False)
        else:
            stream.write_bool(True)
            stream.write_int(self.init_health)
    def __repr__(self):
        return "BuildProperties(" + \
            repr(self.options) + "," + \
            repr(self.init_health) + \
            ")"
