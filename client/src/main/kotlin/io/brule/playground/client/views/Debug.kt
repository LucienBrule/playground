package io.brule.playground.client.views

import io.brule.playground.client.lib.components.View
import react.FC
import react.Key
import react.Props


external interface DebugProps : Props

val DebugComponent = FC<DebugProps> {
    +"Debug"
}

class DebugView(
    override val path: String,
    override val label: String,
    override var props: DebugProps? = null
) : View<DebugProps>(DebugComponent, props) {
    companion object {
        fun create(path: String, label: String): DebugView {
            return DebugView(path, label, object : DebugProps {
                override var key: Key? = "DebugView"
            })
        }
    }
}