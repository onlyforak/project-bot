package com.onlyforak.onlyforakbot.service

import com.onlyforak.onlyforakbot.config.UsersConfig
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

@Service
class NotificationServiceImpl(
    private val bot: TelegramBot,
    private val usersConfig: UsersConfig
) : NotificationService {
    override fun sendNotification(text: String) {
        bot.executeMethod(
            SendMessage(usersConfig.userIdForNotify, text)
        )
    }
}