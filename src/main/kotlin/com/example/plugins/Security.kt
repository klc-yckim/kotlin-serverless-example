package com.example.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.typesafe.config.ConfigFactory
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.config.*

fun Application.configureSecurity() {
    val config = HoconApplicationConfig(ConfigFactory.load())
    val jwtRealm = "sample"
    val jwtSecret = config.property("security.jwt.secret").getString()
    authentication {
        jwt {
            realm = jwtRealm
            verifier(
                JWT
                    .require(Algorithm.HMAC256(jwtSecret))
                    .build()
            )
            validate { credential ->
                JWTPrincipal(credential.payload)
                // TODO 토큰 유효 여부만 확인 중 -> 클레임 값을 기준으로 실제 존재하는 유저인지에 대한 정보 검증 등의 절차 추가 가능
            }
        }
    }
}
