package com.github.leandrochp.jwtvalidationservice.domain.service.impl

import com.github.leandrochp.jwtvalidationservice.common.LoggableClass
import com.github.leandrochp.jwtvalidationservice.domain.JwtPayloadParser
import com.github.leandrochp.jwtvalidationservice.domain.service.JwtService
import org.springframework.stereotype.Service

@Service
class JwtServiceImpl(
    private val jwtPayloadParser: JwtPayloadParser
) : JwtService, LoggableClass() {
    override fun validate(token: String) = try {
        logger.info("Received request to validate a token: $token")
        jwtPayloadParser.valueOf(token).validate().also {
            logger.info("The token $token is [valid: $it]")
        }
    } catch (e: Exception) {
        logger.error("The token $token is not valid [${e.message}]")
        false
    }
}
