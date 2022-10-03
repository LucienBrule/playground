import react.FC
import react.Props
import react.dom.html.InputType
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.input
import react.useState

external interface AppProps : Props {
    var name: String
}

val App = FC <AppProps> { props ->
    val (count, setCount) = useState(0)

    div {
        +"Count: $count"
    }
    input {
        type = InputType.button
        value = "Increment"
        onClick = { setCount(count + 1) }
    }
}