package io.brule.playground.client.views

import csstype.*
import emotion.react.css
import io.brule.playground.client.lib.api.CursorApi
import io.brule.playground.client.lib.api.CursorPosition
import io.brule.playground.client.lib.components.View
import kotlinx.browser.document
import react.FC
import react.Props
import react.dom.html.InputType
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.pre
import react.useEffect
import react.useState


external interface CursorsProps : Props {
}

val CursorsComponent = FC<CursorsProps> {

     /*
        We are going to make an input box that will accept text and then the render method will render the text
        This will prove that useState works as intended.
    */

//    val cursorApi = CursorApi.getInstance()

    val (cursor, setCursor) = useState(CursorPosition(0, 0))

    val (text, setText) = useState("")


    useEffect(cursor) {
        console.log("Cursor Effect Ran")

        fun reportCursorMovement() {
            console.log("Cursor moved to new position", cursor)
        }

        reportCursorMovement()

    }

    useEffect{

        fun hookMouseMovement(){
            console.log("Hooking Mouse Movement")
            return document.addEventListener("mousemove", { event ->
                val x = event.asDynamic().clientX
                val y = event.asDynamic().clientY
                setCursor(CursorPosition(x, y))
            })
        }

        if(cursor.x == 0 && cursor.y == 0) {
            hookMouseMovement()
        }

    }

    useEffect(text) {
        console.log("Text changed")
    }

    h1 {
        +"Cursors"
    }

    div{
        +"Input: "

        input{
            type = InputType.text
            onChange = {
                setText(it.target.value)
            }
        }

        +"Text: $text"
    }
    div{
        +"Cursor: ${cursor.x}, ${cursor.y}"
    }

    div{
        css {
            position = Position.absolute
            top = cursor.y.px
            left = cursor.x.px
            width = 100.px
            height = 100.px
            backgroundColor = NamedColor.red
        }
    }


}

class CursorsView(
    override val path: String,
    override val label: String,
    override var props: CursorsProps? = null
) : View<CursorsProps>(CursorsComponent, props)