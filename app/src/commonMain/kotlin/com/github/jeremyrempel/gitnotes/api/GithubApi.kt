package com.github.jeremyrempel.gitnotes.api

import com.github.jeremyrempel.gitnotes.api.data.ReadMeResponse

interface GithubApi {
    suspend fun getReadme(): ReadMeResponse
}