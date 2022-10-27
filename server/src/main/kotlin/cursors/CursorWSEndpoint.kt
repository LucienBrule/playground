package io.brule.playground.cursors

import io.quarkus.runtime.StartupEvent
import org.jboss.logging.Logger
import java.util.concurrent.ConcurrentHashMap
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.event.Observes
import javax.websocket.*
import javax.websocket.server.PathParam
import javax.websocket.server.ServerEndpoint

@ServerEndpoint("/api/chat/{username}")
@ApplicationScoped
class DebugEndpoint(
    val logger: Logger
){

    val sessions = ConcurrentHashMap<String, Session>()

    init{
        logger.info("DebugEndpoint initialized")
    }

    fun observesApplicationStartup(
        @Observes startupEvent: StartupEvent
    ){
        logger.info("DebugEndpoint observesApplicationStartup")
    }

    @OnOpen
    fun onOpen(session: Session, @PathParam("username") username: String) {
        logger.info("Session ${session.id} opened for user $username")
        sessions[username] = session
    }

    @OnClose
    fun onClose(session: Session, @PathParam("username") username: String) {
        logger.info("Session ${session.id} closed for user $username")
        sessions.remove(username)
    }

    @OnError
    fun onError(session: Session, throwable: Throwable, @PathParam("username") username: String) {
        logger.info("Session ${session.id} for user $username encountered error: ${throwable.message}")
        sessions.remove(username)
    }

    @OnMessage
    fun onMessage(session: Session, message: String, @PathParam("username") username: String) {
        logger.info("Session ${session.id} received message $message")
        broadcast(message)
    }

    fun broadcast(message: String) {
        sessions.forEach { (_, session) ->
            session.asyncRemote.sendText(message)
        }
    }

}


@ServerEndpoint("/api/cursors/ws/{id}")
@ApplicationScoped
class CursorWSEndpoint(
    val logger: Logger,
) {

    init {
        logger.info("CursorWSEndpoint initialized")
    }

    val sessions = ConcurrentHashMap<String, Session>()



    @OnOpen
    fun onOpen(session: Session, @PathParam("id") id: String) {
        sessions[id] = session
        logger.info("Session opened: $id")
    }

    @OnClose
    fun onClose(session: Session, @PathParam("id") id: String) {
        sessions.remove(id)
        logger.info("Session closed: $id")
        broadcast("user $id left")
    }

    @OnError
    fun onError(
        session: Session,
        @PathParam("id") id: String,
        throwable: Throwable
    ) {
        sessions.remove(id)
        logger.error("error for session $id", throwable)
        broadcast("user $id left")
    }

    @OnMessage
    fun onMessage(
        session: Session,
        @PathParam("id") id: String,
        message: String
    ) {
        logger.info("message from $id: $message")
        broadcast("user $id: $message")
    }


    fun broadcast(message: String) {
        sessions.forEach {
            it.value.asyncRemote.sendObject(message) {
                if (it.exception != null) {
                    logger.info("error sending message")
                }
            }
        }
    }
}