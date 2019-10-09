package com.github.jeremyrempel.gitnotes.api.mock

import com.github.jeremyrempel.gitnotes.api.RepoInfo
import com.github.jeremyrempel.gitnotes.api.data.ContentsResponseRow
import com.github.jeremyrempel.gitnotes.repo.SettingsRepo

object Fakes {
    val Repo = RepoInfo("fakeuser", "fakerepo")

    val Settings = object : SettingsRepo {
        override fun save(repoInfo: RepoInfo) {
        }

        override fun get() = Repo
    }

    val ContentsResponse = ContentsResponseRow("name", "path", "type", 100, "http://github.com/blah")
}