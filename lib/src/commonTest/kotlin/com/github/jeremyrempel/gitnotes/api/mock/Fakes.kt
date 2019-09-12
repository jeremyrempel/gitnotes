package com.github.jeremyrempel.gitnotes.api

import com.github.jeremyrempel.gitnotes.api.data.ContentsResponseRow

object Fakes {
    val Repo = RepoInfo("fakeuser", "fakerepo")
    val ContentsResponse = ContentsResponseRow("name", "path", "type", 100, "http://github.com/blah")
}