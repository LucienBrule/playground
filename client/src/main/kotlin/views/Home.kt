import csstype.ClassName
import csstype.HtmlAttributes
import io.brule.SearchQuery
import io.brule.SearchResult
import io.brule.SearchResults
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import react.FC
import react.Props
import react.dom.html.InputType
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.input
import react.useState
import react.dom.html.ReactHTML.h3
import kotlin.js.Promise

external interface HomeProps : Props {
    var placeholder: String;
    var label : String;
}


val Home = FC<HomeProps> {
//    use state to store the value of the input
    val (query: String, setQuery) = useState("")
    val (results, setResults) = useState<SearchResults>(SearchResults(emptyList()))

    val search = {
        val searchQuery = SearchQuery(query)
        GlobalScope.launch {
            setResults( SearchApi().search(searchQuery))
        }
    }

    div {
        className = ClassName("Home")
        div {
            className = ClassName("HomeContent")
            h3{
                +"Playground"
            }
            input {
                type = InputType.text
                placeholder = it.placeholder
                onChange = {
                    setQuery(it.target.value)
                    search()
                }
            }
            button{
                +"Search"
                onClick = {
                    search()
                }
            }
        }

        div{
            className = ClassName("HomeLiveSearch")
            +"Live Search"


            results.results.map { result ->
                div {
                    +result.toString()
                }
            }
        }
    }
}