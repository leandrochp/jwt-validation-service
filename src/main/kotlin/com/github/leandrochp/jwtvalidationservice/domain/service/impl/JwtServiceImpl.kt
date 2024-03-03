package com.github.leandrochp.jwtvalidationservice.domain.service.impl

import com.github.leandrochp.jwtvalidationservice.domain.service.JwtService
import org.springframework.stereotype.Service

@Service
class JwtServiceImpl: JwtService {
    override fun validate(token: String): Boolean {
        return false
    }
}
