package com.github.jeremyrempel.gitnotes.api

import com.github.jeremyrempel.gitnotes.api.data.ContentsResponse
import com.github.jeremyrempel.gitnotes.api.data.ContentsResponseRow
import com.github.jeremyrempel.gitnotes.api.data.ReadMeResponse

class GithubApiFake : GithubApi {
    val data = ContentsResponse.ListResponse(
        listOf(
            ContentsResponseRow("name", "file", "filepath", 100, "http://github.com/myfile")
        )
    )

    override suspend fun getReadme(): ReadMeResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getContents(repoInfo: RepoInfo, path: String?): ContentsResponse = data
}