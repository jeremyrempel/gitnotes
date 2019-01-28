package sample

expect class Sample() {
    fun checkMe(): Int
}

expect object Platform {
    val name: String
}

fun hello(): String = "Hello from ${Platform.name}"

class Proxy {
    fun proxyHello() = "${hello()}, checkMe: ${Sample().checkMe()}"
}

fun main() {
    println(hello())
}