package io.brule.openai

import io.ktor.resources.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/*
 See https://ktor.io/docs/type-safe-request.html#make_requests
 and https://beta.openai.com/docs/api-reference/completions/create
 and https://github.com/Aallam/openai-kotlin/blob/main/openai-core/src/commonMain/kotlin/com.aallam.openai.api/completion/CompletionRequest.kt (MIT)
*/

@Serializable
@Resource("/v1/completions")
class CompletionRequest(

    @SerialName("model")
    val model: String,

    @SerialName("prompt")
    val prompt: String,

    @SerialName("max_tokens")
    val maxTokens: Int,

    @SerialName("temperature")
    val temperature: Double,

    @SerialName("top_p")
    val topP: Double,

    @SerialName("n")
    val n: Int,

    @SerialName("stream")
    val stream: Boolean,

    @SerialName("logprobs")
    val logProbs: Int,

    @SerialName("echo")
    val echo: Boolean,

    @SerialName("stop")
    val stop: List<String>,

    @SerialName("presence_penalty")
    val presencePenalty: Double,

    @SerialName("frequency_penalty")
    val frequencyPenalty: Double,

    @SerialName("best_of")
    val bestOf: Int,

    @SerialName("logit_bias")
    val logitBias: Map<String, Double>,

    @SerialName("user")
    val user: String,
)