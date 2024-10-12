package com.github.leandrochp.jwtvalidationservice.domain

import com.auth0.jwt.JWT
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtPayloadParser(
    private val objectMapper: ObjectMapper
) {
    init {
        objectMapper.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
    }

    fun valueOf(token: String): JwtPayload = try {
        val validToken = JWT.decode(token)
        val charset = charset("UTF-8")
        val payload = String(
            Base64.getDecoder().decode(validToken.payload.toByteArray(charset)),
            charset
        )
        objectMapper.readValue(payload, JwtPayload::class.java)
    } catch (e: Exception) {
        throw Exception("Error parsing JWT: ${e.message}")
    }

}
