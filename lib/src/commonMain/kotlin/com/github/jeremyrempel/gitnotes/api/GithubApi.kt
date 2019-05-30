package com.github.jeremyrempel.gitnotes.api

import com.github.jeremyrempel.gitnotes.api.data.ContentsResponseRow
import com.github.jeremyrempel.gitnotes.api.data.ReadMeResponse

interface GithubApi {
    suspend fun getReadme(): ReadMeResponse

    suspend fun getContents(repoInfo: RepoInfo, path: String? = null): List<ContentsResponseRow>
}

data class RepoInfo(val user: String, val repo: String)