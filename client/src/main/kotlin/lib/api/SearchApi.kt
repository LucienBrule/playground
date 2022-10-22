
import io.brule.SearchQuery
import io.brule.SearchResults
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.js.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*

import io.ktor.http.*

import io.ktor.serialization.kotlinx.json.*
import kotlinx.browser.window
import kotlinx.serialization.json.Json


/*
* Api.kt - client
*
* Implements REST API calls to the server.
* */

class SearchApi {

    private val url = "http://${window.location.host}/api/search"

    companion object {
        private val client = HttpClient(Js) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    encodeDefaults = true
                })
            }
        }
    }

    suspend fun search(query: SearchQuery): SearchResults {
        val response = client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(query)
        }
        return response.body<SearchResults>()
    }

}
