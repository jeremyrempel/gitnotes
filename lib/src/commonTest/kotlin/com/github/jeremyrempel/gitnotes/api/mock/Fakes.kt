package com.github.jeremyrempel.gitnotes.api

import com.github.jeremyrempel.gitnotes.api.data.ContentsResponseRow
import com.github.jeremyrempel.gitnotes.repo.SettingsRepo

object Fakes {
    val Repo = RepoInfo("fakeuser", "fakerepo")

    val Settings = object : SettingsRepo {
        override fun updateRepoName(username: String, repoName: String) {
        }

        override fun getRepoInfo() = Repo
    }

    val ContentsResponse = ContentsResponseRow("name", "path", "type", 100, "http://github.com/blah")
}