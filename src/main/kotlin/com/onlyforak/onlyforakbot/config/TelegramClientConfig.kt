package com.onlyforak.onlyforakbot.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Service

@Service
@ConfigurationProperties(prefix = "telegram")
data class TelegramClientConfig(@Value("\${telegram.username}") val username: String,
                                @Value("\${telegram.token}") val token: String,)