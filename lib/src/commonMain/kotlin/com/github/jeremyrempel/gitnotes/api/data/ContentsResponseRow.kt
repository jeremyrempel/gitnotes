package com.github.jeremyrempel.gitnotes.api.data

import kotlinx.serialization.Serializable

/**
 * https://api.github.com/repos/jeremyrempel/gitnotestest/contents/
 */
@Serializable
data class ContentsResponseRow(
    val name: String,
    val path: String,
    val type: String, // file, dir or symlink
    val size: Long,
    val url: String,
    val content: String? = null
)

sealed class ContentsResponse {
    class ListResponse(data: List<ContentsResponseRow>) : ContentsResponse()
    class ObjectResponse(data: ContentsResponseRow) : ContentsResponse()
}