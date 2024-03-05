package com.github.leandrochp.jwtvalidationservice.common

import org.slf4j.Logger
import org.slf4j.LoggerFactory

abstract class LoggableClass {
    val logger: Logger = LoggerFactory.getLogger(this::class.java)
}
