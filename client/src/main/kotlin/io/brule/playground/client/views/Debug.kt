package io.brule.playground.client.views

import io.brule.playground.client.lib.components.View
import react.FC
import react.Props


external interface DebugProps: Props {

}

val DebugComponent = FC <DebugProps> {
    + "Debug"
}

class DebugView(
    override val path: String,
    override val label: String
    ) : View<DebugProps>(DebugComponent)