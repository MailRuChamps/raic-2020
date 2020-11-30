class BuildProperties {
    constructor(options, initHealth) {
        this.options = options;
        this.initHealth = initHealth;
    }
    static async readFrom(stream) {
        let options;
        options = [];
        for (let i = await stream.readInt(); i > 0; i--) {
            let optionsElement;
            optionsElement = await stream.readInt();
            options.push(optionsElement);
        }
        let initHealth;
        if (await stream.readBool()) {
            initHealth = await stream.readInt();
        } else {
            initHealth = null;
        }
        return new BuildProperties(options, initHealth);
    }
    async writeTo(stream) {
        let options = this.options;
        await stream.writeInt(options.length);
        for (let optionsElement of options) {
            await stream.writeInt(optionsElement);
        }
        let initHealth = this.initHealth;
        if (initHealth === null) {
            await stream.writeBool(false);
        } else {
            await stream.writeBool(true);
            await stream.writeInt(initHealth);
        }
    }
}
module.exports = BuildProperties
