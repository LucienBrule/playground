package io.brule.search

import io.brule.SearchQuery
import io.brule.SearchResult
import io.brule.SearchResults
import io.ktor.util.*
import kotlinx.serialization.json.Json
import org.jboss.logging.Logger
import java.security.MessageDigest
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.sse.OutboundSseEvent
import javax.ws.rs.sse.Sse
import javax.ws.rs.sse.SseEventSink

@ApplicationScoped
@Path("/api/search")
class SearchEndpoint(
    val logger: Logger,
    val searchService: ISearchService
) {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    suspend fun search(query: SearchQuery): SearchResults {
        logger.info("searching for ${query.query}")
        return searchService.search(query)
    }

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @Path("/stream")
    suspend fun searchStream(
        @QueryParam("q") query: String,
        @Context sink: SseEventSink,
        @Context sse: Sse
    ) {
        logger.info("searching for $query")

        searchService
            .searchStream(SearchQuery(query))
            .onItem().invoke { result ->
                logger.info("got result $result")
            }
            .onFailure().invoke { err ->
                logger.error("got error $err")
            }
            .onCompletion().invoke {
                logger.info("done")
                sink.close()
            }
            .subscribe()
            .with { result ->
                logger.info("sending result $result")
                val serialized =
                    Json.encodeToString(SearchResult.serializer(), result)
                val id = MessageDigest
                    .getInstance("SHA-256")
                    .digest(serialized.toByteArray())
                    .encodeBase64()

                val event: OutboundSseEvent.Builder = sse.newEventBuilder()
                    .id(id)
                    .mediaType(MediaType.APPLICATION_JSON_TYPE)
                    .data(serialized)

                sink.send(event.build())
            }

    }
}