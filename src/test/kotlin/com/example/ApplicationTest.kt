package com.example

import com.example.persistences.config.DatabaseSingleton
import com.example.plugins.configureRouting
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
//            configureSecurity()
            DatabaseSingleton.init()
            install(ContentNegotiation) {
                jackson()
            }
            configureRouting()
        }
        client.get("/v1/articles").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
    }
}
