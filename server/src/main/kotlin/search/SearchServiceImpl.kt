package io.brule.search

import io.brule.Config
import io.brule.SearchQuery
import io.brule.SearchResult
import io.brule.SearchResults
import io.ktor.util.reflect.*
import io.smallrye.mutiny.Multi
import io.smallrye.mutiny.Uni
import io.smallrye.mutiny.helpers.spies.Spy.onFailure
import io.smallrye.mutiny.helpers.spies.Spy.onItem
import io.smallrye.mutiny.operators.uni.UniBlockingAwait.await
import io.vertx.core.eventbus.DeliveryOptions
import io.vertx.core.eventbus.MessageCodec
import io.vertx.core.json.JsonObject
import io.vertx.mutiny.core.eventbus.EventBus
import org.jboss.logging.Logger
import java.util.concurrent.TimeUnit
import java.util.function.Function
import javax.enterprise.context.ApplicationScoped
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@ApplicationScoped
class SearchServiceImpl(
    val logger: Logger,
    val bus: EventBus,
    val config: Config
) : ISearchService {

    companion object{
        fun getSearchResults(query: SearchQuery): SearchResults{
            return (0..10).map {
                SearchResult(
                    title = "test ${query.query}",
                    description = "your query was ${query.query}",
                    url = "http://localhost:8080"
                )
            }.let {
                SearchResults(it)
            }
        }
    }



    override suspend fun search(query: SearchQuery): SearchResults {
        logger.info("searching for ${query.query}")
        return getSearchResults(query)
    }

    override suspend fun searchStream(query: SearchQuery): Multi<SearchResult> {
        logger.info("searching for ${query.query}")

        if(!config.isDebug()){
            return Multi.createFrom().items(
                SearchResult(
                    title = query.query,
                    description = "The first result",
                    url = "http://localhost:8080"
                ),
                SearchResult(
                    title = query.query,
                    description = "The second result",
                    url = "http://localhost:8080"
                )
            )
        }
        else{
            logger.info("sending it over the bus")

             return bus
                 .request<SearchResult>("search",query, DeliveryOptions())
                 .toMulti()
                 .onItem().transform { it.body() }

        }


    }

}