package com.onlyforak.onlyforakbot.service

import com.onlyforak.onlyforakbot.config.TelegramClientConfig
import com.onlyforak.onlyforakbot.config.TextConfig
import com.onlyforak.onlyforakbot.config.UsersConfig
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service
import org.telegram.telegrambots.bots.DefaultBotOptions
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.send.SendSticker
import org.telegram.telegrambots.meta.api.objects.InputFile
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException
import org.telegram.telegrambots.meta.generics.BotOptions
import java.io.Serializable

@Service
class TelegramBotImpl(
    private val telegramClientConfig: TelegramClientConfig,
    private val usersConfig: UsersConfig,
    private val textConfig: TextConfig,
    @Lazy
    private val notificationService: NotificationService
) : TelegramBot, TelegramLongPollingBot(
    DefaultBotOptions()
) {
    private val logger = LoggerFactory.getLogger(TelegramBotImpl::class.java)

    private var flag:Boolean = false

    override fun <T : Serializable?, Method : BotApiMethod<T>?> executeMethod(method: Method?): T {
        try {
            return execute(method)
        } catch (e: TelegramApiRequestException) {
            throw Exception("Ошибка при обращении к апи телеграмма: " + e.apiResponse + ". " + e.localizedMessage)
        }
    }

    override fun getBotUsername(): String {
        return telegramClientConfig.username
    }

    override fun getBotToken(): String {
        return telegramClientConfig.token
    }

    override fun onUpdateReceived(update: Update?) {
        logger.info(update.toString())
        val user = update!!.message.from
        if (user.userName == usersConfig.targetUsername && !flag){
            execute(SendMessage(
                user.id.toString(),
                textConfig.textForTargetUser
            ))
            execute(SendMessage(
                user.id.toString(),
                textConfig.nextTextForTargetUser
            ))
            notificationService.sendNotification("Bot sent message to target")
            flag = true
        }
    }
}