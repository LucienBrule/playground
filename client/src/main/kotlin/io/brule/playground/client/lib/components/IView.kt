package io.brule.playground.client.lib.components

import io.brule.playground.client.lib.router.Routable
import kotlinx.serialization.Serializable
import react.FC
import react.Props


/*
 * This is a generic interface for a view.
 * It is used to define a view's path, label, and component.
*/
interface IView<T : Props> : Routable {
    val component: FC<T>
    val props: T?
}


@Serializable
abstract class View<T>(
    override var component: FC<T>,
    override val props: T?
) : IView<T> where T : Props

