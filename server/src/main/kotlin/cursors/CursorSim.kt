package io.brule.cursors

import com.badlogic.gdx.physics.box2d.*
import ktx.box2d.createWorld
import ktx.box2d.earthGravity

class CursorSim {

    val world = createWorld(gravity =  earthGravity)

    fun step(){
        println("step")
        world.step(1f/60f,6,2)
    }

    init {
        world.setContactListener(CursorContactListener())

    }

}

class CursorContactListener : ContactListener {
    override fun beginContact(contact: Contact) {
        println("begin contact")
    }

    override fun endContact(contact: Contact) {
        println("end contact")
    }

    override fun preSolve(contact: Contact, oldManifold: Manifold) {
        println("pre solve")
    }

    override fun postSolve(contact: Contact, impulse: ContactImpulse) {
        println("post solve")
    }
}