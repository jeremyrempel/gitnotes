package com.github.jeremyrempel.gitnotes.api

import com.github.jeremyrempel.gitnotes.api.data.ContentsResponse
import com.github.jeremyrempel.gitnotes.api.data.ReadMeResponse

class GithubApiFake : GithubApi {
    val data = listOf(
        ContentsResponse("name", "file", 100, "http://github.com/myfile")
    )

    override suspend fun getReadme(): ReadMeResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getContents(repoInfo: RepoInfo, path: String?): List<ContentsResponse> = data
}