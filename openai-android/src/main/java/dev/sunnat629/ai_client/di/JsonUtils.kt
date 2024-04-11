package dev.sunnat629.ai_client.di

import android.content.Context
import android.net.Uri
import androidx.annotation.RawRes
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import org.json.JSONException
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.BufferedReader

object JsonUtils : KoinComponent {
    val json: Json by inject()

    @JvmStatic
    inline fun <reified T> convertJsonToObject(jsonObject: JsonObject): T {
        return convertJsonToObject<T>(jsonObject.toString())
    }

    @JvmStatic
    inline fun <reified T> convertJsonToObject(jsonString: String): T {
        return try {
            jsonString.let { json.decodeFromString<T>(it) }
        } catch (e: JSONException) {
            e.printStackTrace()
            throw e
        }
    }

    @JvmStatic
    inline fun <reified T> convertObjectToJson(modelClass: T): String =
        json.encodeToString(modelClass)

    @JvmStatic
    fun convertStringFromRaw(context: Context, @RawRes rawFile: Int): String {
        return context.resources.openRawResource(rawFile).bufferedReader().use { it.readText() }
    }

    @JvmStatic
    fun convertTxtToJsonl(context: Context?, uri: Uri): String? {
        val inputStream = context?.contentResolver?.openInputStream(uri) ?: return null
        val txtContent = inputStream.bufferedReader().use(BufferedReader::readText)

        return txtContent.lineSequence()
            .filter { it.isNotBlank() }
            .joinToString("\n") { """{"data": "$it"}""" }
    }
}