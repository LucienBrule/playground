package io.brule.playground.client.lib.components

import io.brule.playground.client.lib.router.Routable
import kotlinx.serialization.Serializable
import react.*


/*
 * This is a generic interface for a view.
 * It is used to define a view's path, label, and component.
*/
@JsName("IView")
interface IView<T : Props> : Routable {
    @JsName("component")
    var component: FC<T>
    @JsName("props")
    var props: T?
    @JsName("getElement")
    fun getElement(): ReactElement<T>
}


@Serializable
abstract class View<T>(
    override var component: FC<T>,
    override var props: T?
) : IView<T> where T : Props {

    init {
        console.log("View created:", this)
    }

    override fun getElement(): ReactElement<T> {
        return props?.let { createElement(component, it) } ?: component.create()
    }
}
