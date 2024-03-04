package com.github.leandrochp.jwtvalidationservice.domain

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtPayloadParser(
    private val objectMapper: ObjectMapper
) {

    fun valueOf(token: String): JwtPayload = try {
        val charset = charset("UTF-8")
        val parts = token.split(".")
        val payload = String(Base64.getDecoder().decode(parts[1].toByteArray(charset)), charset)

        objectMapper.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        objectMapper.readValue(payload, JwtPayload::class.java)
    } catch (e: Exception) {
        throw Exception("Error parsing JWT: ${e.message}")
    }

}
