package dev.sunnat629.openai_android.clients.models

import dev.sunnat629.openai_android.apis.models.ModelsRepository
import dev.sunnat629.openai_android.clients.BaseUseCases
import dev.sunnat629.openai_android.models.models.ListModelsResponse
import dev.sunnat629.openai_android.models.models.ModelResponse
import dev.sunnat629.openai_android.networks.ApiResult


interface Models : BaseUseCases<Models> {

    suspend fun getModels(): ApiResult<ListModelsResponse>
    suspend fun retrieveModel(): ApiResult<ModelResponse>
}


internal class ModelsImpl(private val repository: ModelsRepository) : Models {

    private var _model: String? = null
    private var _role: String? = null
    private var _text: String? = null
    private var _imageUrl: String? = null

    override fun model(model: String): Models {
        this._model = model
        return this
    }

    override fun role(role: String): Models {
        this._role = role
        return this
    }

    override fun text(text: String): Models {
        this._text = text
        return this
    }

    override fun imageUrl(imageUrl: String): Models {
        this._imageUrl = imageUrl
        return this
    }

    override suspend fun getModels(): ApiResult<ListModelsResponse> {
        return repository.listModels()
    }

    override suspend fun retrieveModel(): ApiResult<ModelResponse> {
        return repository.retrieveModel(_model!!)
    }
}