package com.github.leandrochp.jwtvalidationservice.application.web.controller

import com.github.leandrochp.jwtvalidationservice.application.web.request.JwtValidationRequest
import com.github.leandrochp.jwtvalidationservice.application.web.response.JwtValidationResponse
import com.github.leandrochp.jwtvalidationservice.domain.service.JwtService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class JwtController(
    private val jwtService: JwtService
) {

    @PostMapping("/validate")
    fun validate(@RequestBody request: JwtValidationRequest) =
        JwtValidationResponse(jwtService.validate(request.token))

}
