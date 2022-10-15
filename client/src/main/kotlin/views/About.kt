import react.FC
import react.Props
import react.dom.html.ReactHTML.h1

external interface AboutProps: Props {

}

val About = FC <AboutProps>{
    h1{
        +"About"
    }
}