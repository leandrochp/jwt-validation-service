package com.github.leandrochp.jwtvalidationservice.application.web.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.github.leandrochp.jwtvalidationservice.application.web.request.JwtPayloadRequestMock
import com.github.leandrochp.jwtvalidationservice.application.web.response.JwtValidationResponse
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class JwtControllerComponentTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    private val objectMapper: ObjectMapper = ObjectMapper().registerModule(
        KotlinModule.Builder()
            .withReflectionCacheSize(512)
            .configure(KotlinFeature.NullToEmptyCollection, false)
            .configure(KotlinFeature.NullToEmptyMap, false)
            .configure(KotlinFeature.NullIsSameAsDefault, false)
            .configure(KotlinFeature.SingletonSupport, false)
            .configure(KotlinFeature.StrictNullChecks, false)
            .build()
    )

    @Test
    fun `given a valid token the api should return true`() {
        mockMvc.perform(
            MockMvcRequestBuilders.post("/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JwtPayloadRequestMock.validTokenPayload())
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn().also {
                val response = objectMapper.readValue(it.response.contentAsString, JwtValidationResponse::class.java)
                assert(response.valid)
            }
    }

    @Test
    fun `given an invalid token the api should return false`() {
        mockMvc.perform(
            MockMvcRequestBuilders.post("/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JwtPayloadRequestMock.invalidTokenPayload())
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn().also {
                val response = objectMapper.readValue(it.response.contentAsString, JwtValidationResponse::class.java)
                assert(!response.valid)
            }
    }

    @Test
    fun `given a token with claim name contains number the api should return false`() {
        mockMvc.perform(
            MockMvcRequestBuilders.post("/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JwtPayloadRequestMock.tokenWithNumberClaimNamePayload())
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn().also {
                val response = objectMapper.readValue(it.response.contentAsString, JwtValidationResponse::class.java)
                assert(!response.valid)
            }
    }

    @Test
    fun `given a token with more claims that are permitted the api should return false`() {
        mockMvc.perform(
            MockMvcRequestBuilders.post("/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JwtPayloadRequestMock.tokenWithMoreClaimsPayload())
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn().also {
                val response = objectMapper.readValue(it.response.contentAsString, JwtValidationResponse::class.java)
                assert(!response.valid)
            }
    }

    @Test
    fun `given a token with claim seed value invalid prime number the api should return false`() {
        mockMvc.perform(
            MockMvcRequestBuilders.post("/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JwtPayloadRequestMock.tokenWithInvalidPrimeNumberPayload())
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn().also {
                val response = objectMapper.readValue(it.response.contentAsString, JwtValidationResponse::class.java)
                assert(!response.valid)
            }
    }

    @Test
    fun `given a token with claim name value greater than 256 the api should return false`() {
        mockMvc.perform(
            MockMvcRequestBuilders.post("/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JwtPayloadRequestMock.tokenWithNameMoreThanLengthPayload())
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn().also {
                val response = objectMapper.readValue(it.response.contentAsString, JwtValidationResponse::class.java)
                assert(!response.valid)
            }
    }

    @Test
    fun `given a token with claim invalid role the api should return false`() {
        mockMvc.perform(
            MockMvcRequestBuilders.post("/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JwtPayloadRequestMock.tokenWithInvalidRolePayload())
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn().also {
                val response = objectMapper.readValue(it.response.contentAsString, JwtValidationResponse::class.java)
                assert(!response.valid)
            }
    }

    @Test
    fun `given a token empty the api should return false`() {
        mockMvc.perform(
            MockMvcRequestBuilders.post("/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JwtPayloadRequestMock.tokenEmptyPayload())
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn().also {
                val response = objectMapper.readValue(it.response.contentAsString, JwtValidationResponse::class.java)
                assert(!response.valid)
            }
    }

}