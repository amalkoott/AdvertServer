package com.advert.plugins

import com.example.routes.customerRouting
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import routes.clientRouting
import routes.serverRouting

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
        get("/error-test") {
            throw IllegalStateException("Too Busy")
        }
    }
}

 */
