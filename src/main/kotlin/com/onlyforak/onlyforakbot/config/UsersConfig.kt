package com.onlyforak.onlyforakbot.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Service

@Service
@ConfigurationProperties(prefix = "users")
data class UsersConfig(@Value("\${users.userIdForNotify}") val userIdForNotify: String,
                       @Value("\${users.targetUsername}") val targetUsername: String)