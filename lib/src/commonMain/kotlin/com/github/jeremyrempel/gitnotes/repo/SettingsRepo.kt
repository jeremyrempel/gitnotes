package com.github.jeremyrempel.gitnotes.repo

import com.github.jeremyrempel.gitnotes.api.RepoInfo

interface SettingsRepo {
    fun updateRepoName(username: String, repoName: String)
    fun getRepoInfo(): RepoInfo
}