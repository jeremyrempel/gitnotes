package com.github.jeremyrempel.gitnotes.repo

import com.github.jeremyrempel.gitnotes.api.RepoInfo

interface SettingsRepo {
    fun save(repoInfo: RepoInfo)
    fun get(): RepoInfo
}