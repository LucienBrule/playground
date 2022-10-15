import io.ktor.client.*
import io.ktor.client.engine.js.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

import react.useEffect

/*
* Api.kt - client
*
* Implements REST API calls to the server.
* */

class SearchApi {
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

    suspend fun post(url: String, body: SearchQuery): SearchResult {
        val response = client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(body)
        }
        console.log(response.bodyAsText())
        return Json.decodeFromString(SearchResult.serializer(), response.bodyAsText())
    }

}

// useApi
// a function that implements a react hook to make api calls
// @param url - the url to make the api call to
// @param method - the method to use for the api call
// @param body - the body of the api call
// @param onSuccess - a function to call when the api call is successful
// @param onError - a function to call when the api call is unsuccessful
// @param dependencies - an array of dependencies to watch for changes
// returns a function that can be called to make the api call

fun useSearch(
    url: String,
    method: String,
    body: SearchQuery,
    onSuccess: (ApiDTO) -> Unit,
    onError: (String) -> Unit,
    dependencies: Array<Any>
) {
    useEffect(dependencies) {
        GlobalScope.launch {
            try {
                val response = when (method) {
                    "POST" -> SearchApi().post(url, body)
                    else -> throw Exception("Invalid method")
                }
                onSuccess(response)
            } catch (e: Exception) {
                onError(e.message ?: "An error occurred")
            }
        }
    }
}
