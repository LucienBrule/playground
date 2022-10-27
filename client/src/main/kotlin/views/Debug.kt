import react.FC
import react.Props
import react.dom.html.ReactHTML.h1

external interface DebugProps: Props {

}

val Debug = FC <DebugProps>{
    h1{
        +"Debug"
    }
}