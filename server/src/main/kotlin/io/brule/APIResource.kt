package io.brule


import kotlinx.serialization.Serializable
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import org.jboss.logging.Logger

@ApplicationScoped
@Path("/api")
class APIResource{

    @Inject
    lateinit var logger: Logger


    // returns a DummySearchResult
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    fun search(query: String): DummySearchResult {
        logger.info("searching for $query")
        return DummySearchResult("title", "description", "url")
    }
}

@Serializable
data class DummySearchResult(val title: String, val description: String, val url: String)