package com.github.leandrochp.jwtvalidationservice.application.web.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class SwaggerController {

    @GetMapping("/docs")
    fun swaggerRedirect() = "redirect:swagger-ui/index.html"
}
