package dev.sunnat629.openai_android.clients

import dev.sunnat629.openai_android.models.chats.ChatResponse
import dev.sunnat629.openai_android.networks.ApiResult


interface BaseUseCases {

    fun model(model: String): BaseUseCases
    fun role(role: String): BaseUseCases
    fun text(text: String): BaseUseCases
    fun imageUrl(imageUrl: String): BaseUseCases
}