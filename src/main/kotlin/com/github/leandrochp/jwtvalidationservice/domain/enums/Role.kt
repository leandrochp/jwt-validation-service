package com.github.leandrochp.jwtvalidationservice.domain.enums

enum class Role(private val role: String) {
    ADMIN("Admin"),
    MEMBER("Member"),
    EXTERNAL("External");

    override fun toString() = role
}
