package sport_events

import com.sun.jersey.api.container.httpserver.HttpServerFactory

fun main(args: Array<String>) {
    val SERVER = "http://localhost:3690/"
    var server = HttpServerFactory.create(SERVER)
    server?.start()
    println("Server running")
    println("Resources:")
    println("${SERVER}event/all")
    println("${SERVER}event/{team}")
}
