package com.github.jeremyrempel.gitnotes.api

import com.github.jeremyrempel.gitnotes.api.data.ContentsResponse

object Fakes {
    val Repo = RepoInfo("fakeuser", "fakerepo")
    val ContentsResponse = ContentsResponse("name", "path", "type", 100, "http://github.com/blah")
}