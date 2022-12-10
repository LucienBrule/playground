package io.brule

import org.eclipse.microprofile.config.inject.ConfigProperty
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class Config {

    @ConfigProperty(name = "io.brule.env", defaultValue = "debug")
    lateinit var env: String

    @ConfigProperty(name = "io.brule.openai.api-key", defaultValue = "")
    lateinit var openaiApiKey: String

    fun isDebug(): Boolean {
        return env == "debug"
    }
}