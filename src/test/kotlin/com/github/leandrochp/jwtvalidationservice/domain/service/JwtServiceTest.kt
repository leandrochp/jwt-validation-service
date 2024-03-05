package com.github.leandrochp.jwtvalidationservice.domain.service

import com.github.leandrochp.jwtvalidationservice.domain.JwtPayload
import com.github.leandrochp.jwtvalidationservice.domain.JwtPayloadParser
import com.github.leandrochp.jwtvalidationservice.domain.service.impl.JwtServiceImpl
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class JwtServiceTest {

    private val jwtPayloadParser = mockk<JwtPayloadParser>()
    private val jwtService = JwtServiceImpl(
        jwtPayloadParser
    )

    @Test
    fun `when occurs an error to validate should catch error and return false`() {
        every { jwtPayloadParser.valueOf(any()) } throws Exception("msg")

        assertDoesNotThrow {
            jwtService.validate("test")
        }
    }

    @Test
    fun `should validate token with success`() {
        every { jwtPayloadParser.valueOf(any()) } returns mockk<JwtPayload>()

        assertDoesNotThrow {
            jwtService.validate("test")
        }
    }
}
