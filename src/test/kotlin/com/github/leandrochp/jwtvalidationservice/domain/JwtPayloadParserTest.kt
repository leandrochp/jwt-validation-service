package com.github.leandrochp.jwtvalidationservice.domain

import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class JwtPayloadParserTest {

    private val jwtPayloadParser = JwtPayloadParser(
        ObjectMapper()
    )

    companion object {
        @JvmStatic
        fun invalidTokens(): Stream<Arguments> = Stream.of(
            Arguments.of(
                "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiTWVtYmVyIiwiT3JnIjoiQlIiLCJTZWVkIjoiMTQ2MjciLCJOYW1lIjoiVmFsZGlyIEFyYW5oYSJ9.cmrXV_Flm5mfdpfNUVopY_I2zeJUy4EZ4i3Fea98zvY",
                "eyJhbGciOiJzI1NiJ9.dfsdfsfryJSr2xrIjoiQWRtaW4iLCJTZrkIjoiNzg0MSIsIk5hbrUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05fsdfsIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg"
            )
        )
    }

    @ParameterizedTest
    @MethodSource("invalidTokens")
    fun `given a invalid token should throw exception Error parsing JWT`(token: String) {
        val exception = assertThrows<Exception> {
            jwtPayloadParser.valueOf(token)
        }
        assertThat(exception).hasMessageContaining("Error parsing JWT")
    }

    @Test
    fun `given a valid token should be parser successfully`() {
        val token = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg"

        val result = jwtPayloadParser.valueOf(token)
        assertNotNull(result)
    }

}