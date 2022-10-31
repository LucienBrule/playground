package io.brule.playground.client.views

import io.brule.playground.client.lib.api.CursorApi
import io.brule.playground.client.lib.components.View
import react.FC
import react.Props
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.pre
import react.useEffect
import react.useState


external interface CursorsProps : Props {
}

val CursorsComponent = FC<CursorsProps> {


    val cursorApi = CursorApi.getInstance()

    val (cursor, setCursor) = useState(cursorApi.position)

    useEffect(CursorApi.getInstance().watch) {
        console.log("Cursor changed")
        setCursor(cursorApi.position)
    }

    h1 {
        +"Cursors"
    }


    pre {
        +"$cursor"
    }

}

class CursorsView(
    override val path: String,
    override val label: String,
    override var props: CursorsProps? = null
) : View<CursorsProps>(CursorsComponent, props)