package com.github.jeremyrempel.gitnotes.api.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReadMeResponse(
    val name: String,
    val content: String,
    val encoding: String
) {
    @Serializable
    @SerialName("_links")
    data class Links(
        val self: String,
        val git: String,
        val html: String
    )
}