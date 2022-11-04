package io.brule.openai

import io.brule.Config
import io.brule.playground.lib.SearchQuery
import io.brule.playground.lib.SearchResult
import io.brule.playground.lib.SearchResults
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.resources.*
import io.quarkus.vertx.ConsumeEvent
import org.jboss.logging.Logger
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.GET
import javax.ws.rs.Path

@ApplicationScoped
@Path("/api/openai")
class OpenAiEndpoint(
    val config: Config,
    val logger: Logger,
) {

    private val client = HttpClient(CIO) {
        install(Resources)
        install(Auth) {
            bearer {
                loadTokens {
                    BearerTokens(config.openaiApiKey, "")
                }
            }
        }
    }

    @GET
    fun get(): String {
        logger.info("hello")
        return "Hello OpenAI"
    }

    @ConsumeEvent("openai")
    fun openai(query: SearchQuery): SearchResults {
        logger.info("openai got query $query")
        return SearchResults(
            results = listOf(
                SearchResult(
                    title = "Damn Boi",
                    description = "test",
                    url = "http://localhost:8080"
                )
            )
        )
    }
}