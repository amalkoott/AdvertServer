package com.advert.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import routes.clientRouting

fun Application.configureRouting() {
    routing {
        //customerRouting()
        clientRouting()
            //serverRouting()
    }
}
fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
}
// 1-ый урок
/*
fun Application.configureRouting() {
    install(StatusPages) {
        exception<IllegalStateException> { call, cause ->
            call.respondText("App in illegal state as ${cause.message}")
        }
    }
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/test1") {
            val text = "<h1>Hello From Ktor</h1>"
            val type = ContentType.parse("text/html")
            call.respondText(text, type)
        }
        get("/error-cian_rent.html") {
            throw IllegalStateException("Too Busy")
        }
    }
}

 */
