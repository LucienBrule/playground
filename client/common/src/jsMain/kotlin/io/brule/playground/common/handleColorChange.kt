package io.brule.playground.common

import kotlinx.browser.document


actual fun handleColorChange(color: String) {
    document
        ?.head
        ?.querySelector("meta[name=theme-color]")
        ?.setAttribute("content", color)
}
