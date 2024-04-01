/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_android.apis.moderation

import dev.sunnat629.openai_android.networks.ApiResult

interface ModerationsRepository {
    suspend fun createModeration(request: Any): ApiResult<Any>
}