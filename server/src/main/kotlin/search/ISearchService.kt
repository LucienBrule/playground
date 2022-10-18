package io.brule.search

import io.brule.SearchQuery
import io.brule.SearchResult
import io.brule.SearchResults
import io.smallrye.mutiny.Multi

interface ISearchService {
    suspend fun search(query: SearchQuery): SearchResults
    suspend fun searchStream(query: SearchQuery): Multi<SearchResult>
}