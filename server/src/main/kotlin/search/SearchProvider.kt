package io.brule.playground.search.server


import io.brule.playground.lib.SearchQuery
import io.brule.playground.lib.SearchResult
import io.quarkus.vertx.ConsumeEvent
import io.vertx.mutiny.core.eventbus.EventBus
import org.jboss.logging.Logger
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class SearchProvider(
    val logger: Logger,
    val bus: EventBus
) {

    @ConsumeEvent("search")
    fun search(query: SearchQuery): SearchResult {
        logger.info("searching")
        logger.info("got $query")
        return SearchResult(
            title = "test ${query.query}",
            description = "your query was ${query.query}",
            url = "http://localhost:8080"
        )
    }


    /*
     * quarkus doesn't load the class to register a codec if it's not used
     * in an annotated signature, so we need to use this dummy method to make
     * sure the codec is registered.
     */
    @ConsumeEvent("hack")
    fun hack(a: SearchResult){
        TODO()
    }

}