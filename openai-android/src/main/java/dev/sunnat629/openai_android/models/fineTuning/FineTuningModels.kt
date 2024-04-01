package dev.sunnat629.openai_android.models.fineTuning

import kotlinx.serialization.*

/**
 * Request model for creating a fine-tuning job.
 *
 * @param trainingFile The ID of the file containing the training data.
 * @param model The model to be fine-tuned.
 * @param nEpochs The number of epochs to train for.
 */
@Serializable
data class CreateFineTuningRequest(
    @SerialName("training_file") val trainingFile: String,
    @SerialName("model") val model: String,
    @SerialName("n_epochs") val nEpochs: Int? = null
)

/**
 * Response model representing a fine-tuning job.
 *jl
 * @param id The unique identifier of the fine-tuning job.
 * @param objectContent The type of the object, typically "fine-tune".
 * @param status The status of the fine-tuning job (e.g., "queued", "running", "succeeded").
 */
@Serializable
data class FineTuningResponse(
    @SerialName("id") val id: String,
    @SerialName("object") val objectContent: String,
    @SerialName("status") val status: String
)
