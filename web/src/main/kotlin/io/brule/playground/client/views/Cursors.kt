package io.brule.playground.client.views


import csstype.Color
import csstype.PropertyName
import io.brule.playground.client.lib.api.CursorApi
import io.brule.playground.client.lib.components.CursorComponent
import io.brule.playground.client.lib.components.View
import io.brule.playground.lib.CursorPosition
import kotlinx.browser.document
import react.*
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.span


external interface CursorsProps : Props {
}

val CursorsComponent = FC<CursorsProps> {


    val (cursor, setCursor) = useState(CursorPosition(0, 0))

    val cursorApiRef = useRef(CursorApi.getInstance())

    val (updates, setUpdates) = useState(cursorApiRef.current?.getCursors())



    useEffect(cursor) {

        fun reportCursorMovement() {
            cursorApiRef.current?.updateCursor(cursor)
        }

        reportCursorMovement()

    }

    useEffect {

        fun hookMouseMovement() {
            return document.addEventListener("mousemove", { event ->
                val x = event.asDynamic().clientX + 10
                val y = event.asDynamic().clientY + 10
                setCursor(CursorPosition(x, y))
            })
        }

        if (cursor.x == 0 && cursor.y == 0) {
            hookMouseMovement()
        }
    }

    useEffect{

        fun handleOtherCursorUpdates(){
            cursorApiRef.current?.addListener { update ->
                setUpdates(cursorApiRef.current?.getCursors())
            }
        }

        if(updates.isNullOrEmpty()){
            handleOtherCursorUpdates()
        }


    }


    h1 {
        +"Cursors"
    }


    div {
        span {
            +"Current Cursor: ${cursor.x}, ${cursor.y}"
        }

        for (c in cursorApiRef.current?.getCursors()!!) {
            div {
                +"${c.id}: ${c.position.x}, ${c.position.y}"
            }
        }
    }

    for (c in cursorApiRef.current?.getCursors()!!) {
        CursorComponent {
            this.position = c.position
            this.color = Color("#${c.id.substring(0, 6)}")
        }
    }


}

class CursorsView(
    override val path: String,
    override val label: String,
    override var props: CursorsProps? = null
) : View<CursorsProps>(CursorsComponent, props) {
    companion object {
        fun create(path: String, label: String): CursorsView {
            return CursorsView(path, label, object : CursorsProps {
                override var key: Key? = "CursorsView"
            })
        }
    }
}