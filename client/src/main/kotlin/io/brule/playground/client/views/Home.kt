package io.brule.playground.client.views

import csstype.ClassName
import io.brule.playground.client.lib.api.SearchApi
import io.brule.playground.client.lib.components.View
import io.brule.playground.lib.SearchQuery
import io.brule.playground.lib.SearchResults
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import react.FC
import react.Key
import react.Props
import react.dom.html.InputType
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.b
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h3
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.span
import react.useState

external interface HomeProps : Props {
    var placeholder: String
    var label: String
}


val Home = FC<HomeProps> {
//    use state to store the value of the input
    val (query: String, setQuery) = useState("")
    val (results, setResults) = useState<SearchResults>(SearchResults(emptyList()))

    val search = {
        val searchQuery = SearchQuery(query)
        GlobalScope.launch {
            setResults(SearchApi().search(searchQuery))
        }
    }

    div {
        className = ClassName("Home")
        div {
            className = ClassName("HomeContent")
            h3 {
                +"Playground"
            }
            input {
                type = InputType.text
                placeholder = it.placeholder
                onChange = {
                    setQuery(it.target.value)
                }
            }
            button {
                +"Search"
                onClick = {
                    search()
                }
            }
        }

        div {
            className = ClassName("HomeLiveSearch")
            span {

                h3 {
                    +"Live Search"

                }
                if (results.results.isNotEmpty())
                    p {
                        +"Search results for "
                        b {
                            +query
                        }
                    }
            }


            results.results.map { result ->
                div {
                    className = ClassName("HomeLiveSearchResult")
                    b {
                        +result.title
                    }
                    p {
                        +result.description
                    }
                    a {
                        href = result.url
                        +result.url
                    }
                }
            }
        }
    }
}

class HomeView(
    override val path: String,
    override val label: String
) : View<HomeProps>(
    component = Home,
    object : HomeProps {
        override var key: Key? = "Cursors"
        override var placeholder: String = "Search"
        override var label: String = "Home"
    },
) {
    companion object {
        const val path = "/"
        const val label = "Home"
    }
}
