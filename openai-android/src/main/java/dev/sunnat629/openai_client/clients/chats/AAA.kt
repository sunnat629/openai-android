package dev.sunnat629.openai_client.clients.chats


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AAA(
    @SerialName("id")
    val id: String? = null,
    @SerialName("object")
    val objectX: String? = null,
    @SerialName("created")
    val created: Int? = null,
    @SerialName("model")
    val model: String? = null,
    @SerialName("system_fingerprint")
    val systemFingerprint: String? = null,
    @SerialName("choices")
    val choices: List<Choice?>? = null
) {
    @Serializable
    data class Choice(
        @SerialName("index")
        val index: Int? = null,
        @SerialName("delta")
        val delta: Delta? = null,
        @SerialName("logprobs")
        val logprobs: String? = null,
        @SerialName("finish_reason")
        val finishReason: String? = null
    ) {
        @Serializable
        data class Delta(
            @SerialName("role")
            val role: String? = null,
            @SerialName("content")
            val content: String? = null
        )
    }
}