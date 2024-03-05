package com.github.leandrochp.jwtvalidationservice.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.random.Random

class JwtPayloadTest {

    @Test
    fun `given a name greater than 256 should return false`() {
        val jwtPayload = JwtPayload("".padStart(257, 'T'), "Admin", 1)

        val result = jwtPayload.validate()
        assertEquals(false, result)
    }

    @Test
    fun `given an invalid digit in name should return false`() {
        val jwtPayload = JwtPayload("Test ${Random.nextInt(10)}", "Admin", 1)

        val result = jwtPayload.validate()
        assertEquals(false, result)
    }

    @Test
    fun `given an invalid role should return false`() {
        val jwtPayload = JwtPayload("Test", "Internal", 1)

        val result = jwtPayload.validate()
        assertEquals(false, result)
    }

    @Test
    fun `given an invalid prime number should return false`() {
        val jwtPayload = JwtPayload("Test", "Admin", 12)

        val result = jwtPayload.validate()
        assertEquals(false, result)
    }

    @Test
    fun `given a valid payload should return true`() {
        val jwtPayload = JwtPayload("Test", "Admin", 5)

        val result = jwtPayload.validate()
        assertEquals(true, result)
    }

}
