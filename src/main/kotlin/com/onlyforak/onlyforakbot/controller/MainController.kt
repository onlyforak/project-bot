package com.onlyforak.onlyforakbot.controller

import com.onlyforak.onlyforakbot.config.SecurityConfig
import com.onlyforak.onlyforakbot.service.NotificationService
import com.onlyforak.onlyforakbot.service.SimpleSecurityService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController(
    private val notificationService: NotificationService,
    private val securityService: SimpleSecurityService
) {

    @PostMapping("/enter/birthday")
    fun enterPageBirthday(@RequestHeader("accept-language") code: String): String {
        if (securityService.checkCode(code)){
            notificationService.sendNotification("enter birthday")
            return "ok"
        } else throw Exception("Failed")
    }

    @PostMapping("/enter/main")
    fun enterMainPage(@RequestHeader("accept-language") code: String): String {
        if (securityService.checkCode(code)){
            notificationService.sendNotification("enter main")
            return "ok"
        } else throw Exception("Failed")
    }
}