package io.brule.playground.client.lib.components

import csstype.Color
import csstype.HtmlAttributes
import csstype.em
import csstype.pct
import emotion.react.css
import io.brule.playground.client.lib.router.Routable
import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.Serializable
import react.*
import react.dom.html.ReactHTML.div
import react.dom.render
import webgl.OES_vertex_array_object
import kotlin.reflect.typeOf


interface IView<T : Props> : Routable {
    val component: FC<T>
}


@Serializable
abstract class View<T>: IView<T> where T : Props {
    override val component: FC<T>
    override val label: String
    override val path: String


    constructor(component:FC<T>){
        this.component = FC<T> {
            div {
                css {
                    height = 100.pct
                    padding = 3.em
                }
                child(component,it)
            }
        }
        this.label = component.displayName ?: "No Label"
        this.path = "/${component.displayName?.lowercase() ?: "no-path"}"

        component.displayName?.let {
            console.log("View $it created")
        }
    }


    override fun toString(): String {
        return "View(label='$label', path='$path')"
    }
}