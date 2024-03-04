package com.github.leandrochp.jwtvalidationservice.domain

import com.fasterxml.jackson.annotation.JsonProperty
import com.github.leandrochp.jwtvalidationservice.domain.enums.Role

data class JwtPayload(
    @JsonProperty("Name")
    val name: String,
    @JsonProperty("Role")
    val role: String,
    @JsonProperty("Seed")
    val seed: Int
) {

    fun validate(): Boolean {
        if (name.length > 256) return false
        if (name.any { it.isDigit() }) return false
        if (Role.entries.any { it.toString() == role }.not()) return false
        for (i in 2 until seed) {
            if ((seed % i) == 0) return false
        }
        return true
    }
}
