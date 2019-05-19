package com.github.jeremyrempel.gitnotes.api

import com.github.jeremyrempel.gitnotes.api.data.ContentsResponse
import com.github.jeremyrempel.gitnotes.api.data.ReadMeResponse

interface GithubApi {
    suspend fun getReadme(): ReadMeResponse

    /**
     * https://api.github.com/repos/jeremyrempel/gitnotestest/contents/
     */
    suspend fun getContents(): List<ContentsResponse>
}