package io.brule.playground.search.server


import io.brule.playground.lib.SearchQuery
import io.brule.playground.lib.SearchResult
import io.brule.playground.lib.SearchResults
import io.smallrye.mutiny.Multi

interface ISearchService {
    suspend fun search(query: SearchQuery): SearchResults
    suspend fun searchStream(query: SearchQuery): Multi<SearchResult>
}