import csstype.*
import emotion.css.css
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1

external interface HomeProps: Props {

}

val Home = FC <HomeProps>{

   div{
     css {
            display = Display.flex
            flexDirection = FlexDirection.column
            fontFamily = FontFamily.monospace
            justifyContent = JustifyContent.normal
            margin = 1.em
            "div"{
                width = 10.ch
                fontSize = 1.em
            }
     }

       h1{
           +"Home"
       }
   }
}