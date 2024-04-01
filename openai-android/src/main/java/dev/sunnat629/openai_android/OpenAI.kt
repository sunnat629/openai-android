/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_android

import dev.sunnat629.openai_android.models.openaAI.OpenAIBuilderConfig

interface OpenAI {

}

internal class OpenAIImpl(configModel: OpenAIBuilderConfig) : OpenAI {

    init {
        openAiAndroidLibModuleKoin(configModel)
    }
}