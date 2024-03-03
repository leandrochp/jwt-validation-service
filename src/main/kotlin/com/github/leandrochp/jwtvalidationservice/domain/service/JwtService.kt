package com.github.leandrochp.jwtvalidationservice.domain.service

interface JwtService {
    fun validate(token: String): Boolean
}
