import kotlinx.serialization.Serializable
/**
 * ApiDTO.kt - client
 * * Common serializable class for all the api dto's
 */

@Serializable
open class ApiDTO

@Serializable
data class SearchResult(
    val title: String,
    val description: String,
    val url: String
) : ApiDTO()

@Serializable
data class SearchQuery(val query: String) : ApiDTO()