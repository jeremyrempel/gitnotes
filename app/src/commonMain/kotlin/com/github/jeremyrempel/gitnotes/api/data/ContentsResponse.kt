package com.github.jeremyrempel.gitnotes.api.data

import kotlinx.serialization.Serializable

@Serializable
data class ContentsResponse(
    val type: String, // file, dir or symlink
    val encoding: String,
    val size: Int,
    val url: String
)

