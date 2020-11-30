package model

import util.StreamUtil

class EntityProperties {
    var size: Int = 0
    var buildScore: Int = 0
    var destroyScore: Int = 0
    var canMove: Boolean = false
    var populationProvide: Int = 0
    var populationUse: Int = 0
    var maxHealth: Int = 0
    var cost: Int = 0
    var sightRange: Int = 0
    var resourcePerHealth: Int = 0
    var build: model.BuildProperties? = null
    var attack: model.AttackProperties? = null
    var repair: model.RepairProperties? = null
    constructor() {}
    constructor(size: Int, buildScore: Int, destroyScore: Int, canMove: Boolean, populationProvide: Int, populationUse: Int, maxHealth: Int, cost: Int, sightRange: Int, resourcePerHealth: Int, build: model.BuildProperties?, attack: model.AttackProperties?, repair: model.RepairProperties?) {
        this.size = size
        this.buildScore = buildScore
        this.destroyScore = destroyScore
        this.canMove = canMove
        this.populationProvide = populationProvide
        this.populationUse = populationUse
        this.maxHealth = maxHealth
        this.cost = cost
        this.sightRange = sightRange
        this.resourcePerHealth = resourcePerHealth
        this.build = build
        this.attack = attack
        this.repair = repair
    }
    companion object {
        @Throws(java.io.IOException::class)
        fun readFrom(stream: java.io.InputStream): EntityProperties {
            val result = EntityProperties()
            result.size = StreamUtil.readInt(stream)
            result.buildScore = StreamUtil.readInt(stream)
            result.destroyScore = StreamUtil.readInt(stream)
            result.canMove = StreamUtil.readBoolean(stream)
            result.populationProvide = StreamUtil.readInt(stream)
            result.populationUse = StreamUtil.readInt(stream)
            result.maxHealth = StreamUtil.readInt(stream)
            result.cost = StreamUtil.readInt(stream)
            result.sightRange = StreamUtil.readInt(stream)
            result.resourcePerHealth = StreamUtil.readInt(stream)
            if (StreamUtil.readBoolean(stream)) {
                result.build = model.BuildProperties.readFrom(stream)
            } else {
                result.build = null
            }
            if (StreamUtil.readBoolean(stream)) {
                result.attack = model.AttackProperties.readFrom(stream)
            } else {
                result.attack = null
            }
            if (StreamUtil.readBoolean(stream)) {
                result.repair = model.RepairProperties.readFrom(stream)
            } else {
                result.repair = null
            }
            return result
        }
    }
    @Throws(java.io.IOException::class)
    fun writeTo(stream: java.io.OutputStream) {
        StreamUtil.writeInt(stream, size)
        StreamUtil.writeInt(stream, buildScore)
        StreamUtil.writeInt(stream, destroyScore)
        StreamUtil.writeBoolean(stream, canMove)
        StreamUtil.writeInt(stream, populationProvide)
        StreamUtil.writeInt(stream, populationUse)
        StreamUtil.writeInt(stream, maxHealth)
        StreamUtil.writeInt(stream, cost)
        StreamUtil.writeInt(stream, sightRange)
        StreamUtil.writeInt(stream, resourcePerHealth)
        val build = build;
        if (build == null) {
            StreamUtil.writeBoolean(stream, false)
        } else {
            StreamUtil.writeBoolean(stream, true)
            build.writeTo(stream)
        }
        val attack = attack;
        if (attack == null) {
            StreamUtil.writeBoolean(stream, false)
        } else {
            StreamUtil.writeBoolean(stream, true)
            attack.writeTo(stream)
        }
        val repair = repair;
        if (repair == null) {
            StreamUtil.writeBoolean(stream, false)
        } else {
            StreamUtil.writeBoolean(stream, true)
            repair.writeTo(stream)
        }
    }
}
