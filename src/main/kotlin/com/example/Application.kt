package com.example

import com.example.plugins.*
import com.typesafe.config.ConfigFactory
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    val config = HoconApplicationConfig(ConfigFactory.load())
    val port = config.property("ktor.deployment.port").getString().toInt()
    val host = config.property("ktor.deployment.host").getString()

    embeddedServer(Netty, port = port, host = host, module = Application::module)
            .start(wait = true)
}

fun Application.module() {
//    configureSecurity()
    configureRouting()
}
