package com.onlyforak.onlyforakbot.service

import com.onlyforak.onlyforakbot.config.SecurityConfig
import org.springframework.stereotype.Service

@Service
class SimpleSecurityServiceImpl(
    private val securityConfig: SecurityConfig
) : SimpleSecurityService {
    override fun checkCode(code: String): Boolean {
        return securityConfig.code == code
    }
}