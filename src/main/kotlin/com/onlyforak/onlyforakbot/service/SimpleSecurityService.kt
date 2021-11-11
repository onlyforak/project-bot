package com.onlyforak.onlyforakbot.service

interface SimpleSecurityService {
    fun checkCode(code: String): Boolean
}