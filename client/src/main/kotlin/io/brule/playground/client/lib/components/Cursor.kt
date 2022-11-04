package io.brule.playground.client.lib.components


import csstype.Color
import csstype.Position
import csstype.pct
import csstype.px
import emotion.react.css
import io.brule.playground.lib.CursorPosition
import react.FC
import react.Props
import react.dom.html.ReactHTML.div

// A functional component that takes a cursor position as props
// and renders a div with a fixed position at that location.
//

external interface CursorProps : Props {
    var position: CursorPosition
    var color: Color
}

val CursorComponent = FC<CursorProps> { props ->
    div {
        css {
            position = Position.absolute
            top = props.position.y.px
            left = props.position.x.px
            width = 20.px
            height = 20.px
            backgroundColor = props.color
            borderRadius = 50.pct
        }
    }
}