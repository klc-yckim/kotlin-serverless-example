package com.example.plugins

import com.example.models.Article
import com.example.persistences.repository.repository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/v1/articles") {
            call.respond(HttpStatusCode.OK, getArticles())
        }
        get("/") {
            call.respondText("aaa")
        }
    }
}

suspend fun getArticles(): List<Article> {
    return repository.allArticles()
}


