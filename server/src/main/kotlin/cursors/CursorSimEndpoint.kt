package io.brule.cursors

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import io.brule.playground.lib.CursorPosition
import io.brule.playground.lib.CursorUpdate
import io.vertx.mutiny.core.eventbus.EventBus
import ktx.box2d.body
import ktx.box2d.box
import ktx.box2d.circle
import org.jboss.logging.Logger
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.Consumes
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import kotlin.concurrent.thread
import kotlin.math.cos
import kotlin.math.sin

@ApplicationScoped
@Path("/api/cursors")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class CursorSimEndpoint(
    val logger: Logger,
    val bus: EventBus
) {

    val actors = mutableMapOf<String,CursorActor>()



    /**
     * Simulate a client moving a cursor around the screen.
     */
    @PUT
    @Path("/sim")
    fun sim() : String{
        logger.info("CursorSimEndpoint.sim")

        // create a cursor actor and add it to the map
        val actor = CursorActor(bus)
        actors[actor.id] = actor

        // start the actor
        thread {
            actor.run()
        }

        return actor.id
    }
}

class CursorActor(
    val bus: EventBus
): Runnable{



    val id = (0..0xffffff).random().toString(16).padStart(6, '0')
    val position = CursorPosition(0,0)
    override fun run() {

        val sim = CursorSim()
        val body = sim.world.body{
            onCreate {
                println("created body")
            }
           circle(radius = 1f, position = Vector2(0f,0f)){
                density = 1f
                friction = 0.3f
                restitution = 0.5f
           }
        }

        val ground = sim.world.body {
            type= BodyDef.BodyType.StaticBody
            box(width = Float.POSITIVE_INFINITY, height = 1f)
        }
        while(true){

            sim.step()

            position.x = body.position.x.toInt()
            position.y = body.position.y.toInt()

            bus.send("cursors", CursorUpdate(id, position))
            Thread.sleep(100)
        }
    }

    fun update(): CursorUpdate {
        return CursorUpdate(id, position)
    }

}
