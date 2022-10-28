package io.brule.playground.client.views

import io.brule.playground.client.lib.components.View
import react.FC
import react.Key
import react.Props
import react.dom.html.ReactHTML.div


external interface AboutProps : Props

val About = FC<AboutProps> {
    +"About"
    div {
        +"I am a div"

    }
}

class AboutView(override val path: String, override val label: String) :
    View<AboutProps>(About, object : AboutProps {
        override var key: Key? = "About"
    })