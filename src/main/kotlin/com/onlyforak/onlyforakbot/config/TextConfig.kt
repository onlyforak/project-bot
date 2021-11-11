package com.onlyforak.onlyforakbot.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Service

@Service
@ConfigurationProperties(prefix = "text")
data class TextConfig(@Value("\${text.textForTargetUser}") val textForTargetUser: String,
                      @Value("\${text.nextTextForTargetUser}") val nextTextForTargetUser: String)