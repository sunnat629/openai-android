package dev.sunnat629.ai_client.utils

import android.content.Context
import android.net.Uri

/**
 * Reads the content of the file pointed to by the given [Uri] and returns it as a [ByteArray].
 *
 * @param context The [Context] used to access the [ContentResolver].
 * @param uri The [Uri] pointing to the file to read.
 * @return A [ByteArray] containing the file's content, or `null` if the content couldn't be read.
 */
fun uriToByteArray(context: Context?, uri: Uri): ByteArray? = try {
    context?.contentResolver?.openInputStream(uri)?.use { inputStream ->
        inputStream.readBytes()
    }
} catch (e: Exception) {
    e.printStackTrace()
    null
}