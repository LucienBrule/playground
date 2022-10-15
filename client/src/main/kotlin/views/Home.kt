import csstype.ClassName
import csstype.HtmlAttributes
import react.FC
import react.Props
import react.dom.html.InputType
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.input
import react.useState
import react.dom.html.ReactHTML.h3

external interface HomeProps : Props {
    var placeholder: String;
    var label : String;
}


val Home = FC<HomeProps> {
//    use state to store the value of the input
    val (query: String, setQuery) = useState("")
    val (results, setResults) = useState<List<SearchResult>>(listOf())

//    val (results,setResults) = useState(List<Seadd>)
    useSearch(
        url = "http://localhost:9000/api/search",
        method = "POST",
        body = SearchQuery(query),
        onSuccess = { response ->
            console.log(response)
        },
        onError = { error ->
            console.log(error)
        },
        dependencies = arrayOf(query)
    )
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
                }
            }
        }

        div{
            className = ClassName("HomeLiveSearch")
            +"Live Search"


            results.map { result ->
                div {
                    +"${HtmlAttributes.title} - ${result.description}"
                }
            }
        }
    }
}