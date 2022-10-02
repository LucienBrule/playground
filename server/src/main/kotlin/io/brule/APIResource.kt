package io.brule

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/api")
class APIResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun hello() = "Hello from RESTEasy Reactive"

    @Path("/time")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    fun time() = mapOf("time" to System.currentTimeMillis())


    // returns a date like 01/01/2000 00:00:00
    @Path("/date")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    fun date() = mapOf("date" to java.time.LocalDateTime.now().toString())


}