import csstype.*
import csstype.Auto.auto
import emotion.css.css
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1

external interface AppProps : Props {
    var name: String
}

val App = FC<AppProps> { props ->
    Router{}
}