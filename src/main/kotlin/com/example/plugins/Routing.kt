package com.example.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/washnpay/v1/ads") {
            call.respond(HttpStatusCode.OK, getAdsFromDatabase())
        }
    }
}

fun getAdsFromDatabase(): List<String> {
    return listOf("A", "B", "C")
}


