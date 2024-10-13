package com.advert

import com.advert.plugins.configureRouting
import com.advert.plugins.configureSerialization
import io.ktor.server.application.*


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    configureRouting()
    configureSerialization()
}
