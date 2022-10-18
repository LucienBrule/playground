package io.brule

import io.quarkus.runtime.Quarkus
import io.quarkus.runtime.ShutdownEvent
import io.quarkus.runtime.StartupEvent
import io.quarkus.runtime.annotations.QuarkusMain
import io.vertx.core.Vertx
import io.vertx.core.eventbus.MessageCodec
import org.jboss.logging.Logger
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.event.Observes
import javax.inject.Inject

@QuarkusMain
class Application {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Starting Playground Server...")
            Quarkus.run(*args)
        }
    }
}

@ApplicationScoped
class ApplicationLifeCycle(
    private val logger: Logger
) {


    fun onStart(@Observes ev: StartupEvent) {
        logger.info("The application is starting...")
    }

    fun onStop(@Observes ev: ShutdownEvent) {
        logger.info("The application is stopping...")
    }

}


