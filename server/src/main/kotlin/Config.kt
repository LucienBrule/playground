package io.brule

import org.eclipse.microprofile.config.inject.ConfigProperty
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class Config {

    @ConfigProperty(name = "io.brule.env", defaultValue = "debug")
    lateinit var env: String

    fun isDebug(): Boolean {
        return env == "debug"
    }
}