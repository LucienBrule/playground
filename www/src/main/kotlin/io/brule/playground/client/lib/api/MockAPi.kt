package io.brule.playground.client.lib.api

class MockAPI {

    private val previousGreets = mutableListOf<String>()

    init {
        console.log("MockAPI created")
    }

    @JsName("greet")
    fun greet(target: String): String {
        previousGreets.add(target)
        return "Hello, $target!"
    }

    fun getGreeting(idx: Int): String {

        if (idx < 0 || idx >= previousGreets.size) {
            return "No greeting at index $idx"
        }

        return previousGreets[idx]
    }

    fun previousGreetings(): List<String> {
        return previousGreets
    }

    fun numberOfGreetings(): Int {
        return previousGreets.size
    }
}