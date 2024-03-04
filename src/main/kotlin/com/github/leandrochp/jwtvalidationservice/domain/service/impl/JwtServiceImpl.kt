package com.github.leandrochp.jwtvalidationservice.domain.service.impl

import com.github.leandrochp.jwtvalidationservice.domain.JwtPayloadParser
import com.github.leandrochp.jwtvalidationservice.domain.service.JwtService
import org.springframework.stereotype.Service

@Service
class JwtServiceImpl(
    private val jwtPayloadParser: JwtPayloadParser
) : JwtService {
    override fun validate(token: String) = try {
        jwtPayloadParser.valueOf(token).validate()
    } catch (e: Exception) {
        false
    }
}
