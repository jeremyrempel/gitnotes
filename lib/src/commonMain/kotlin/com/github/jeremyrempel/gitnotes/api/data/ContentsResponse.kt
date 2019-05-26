package com.github.jeremyrempel.gitnotes.api.data

import kotlinx.serialization.Serializable

/**
 * https://api.github.com/repos/jeremyrempel/gitnotestest/contents/
 */
@Serializable
data class ContentsResponse(
    val name: String,
    val type: String, // file, dir or symlink
    val size: Long,
    val url: String
)
