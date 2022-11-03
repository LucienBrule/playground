package io.brule.playground.client.views

import io.brule.playground.client.lib.api.MockAPI
import io.brule.playground.client.lib.components.View
import kotlinx.js.Object
import react.*
import react.dom.html.InputType
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.h2
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.label
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ol
import react.dom.html.ReactHTML.pre


external interface AboutProps : Props {
}

val About = FC<AboutProps> { props ->

    val (input: String, setQuery) = useState("")
    val (results, setResults) = useState<String>("")
    val mock = useRef(MockAPI())

    useEffect(input) {
        console.log("input", input)

        fun updateResults() {
            mock.current?.let { setResults(it.greet(input)) }
        }

        updateResults()
    }



    h1 {
        +"About"
    }

    div {


        label {

            +"Enter a name to get a greeting: "

            input {
                type = InputType.text
                placeholder = "Enter some text"
                onChange = {
                    setQuery(it.target.value)
                }
            }
        }

        h2 {
            +"Greeting"
        }

        pre {
            +results
        }

        ol{
            for (greet in mock.current?.previousGreetings()!!){
                li{
                    +greet
                }
            }
        }
    }
}


class AboutView(
    override val path: String,
    override val label: String,
    override var props: AboutProps?
) : View<AboutProps>(About, props){
    companion object {
        fun create(path: String, label: String): AboutView {
            return AboutView(path, label, object : AboutProps{
                override var key: Key? = "AboutView"
            })
        }
    }
}
