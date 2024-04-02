package dev.sunnat629.openai_android.clients


interface BaseUseCases<T> {

    fun model(model: String): T
    fun role(role: String): T
    fun text(text: String): T
    fun imageUrl(imageUrl: String): T
}