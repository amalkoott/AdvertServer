package com.advert.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import routes.clientRouting

fun Application.configureRouting() {
    routing {
        clientRouting()
    }
}
fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
}
