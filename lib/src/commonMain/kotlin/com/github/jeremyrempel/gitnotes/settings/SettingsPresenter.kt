package com.github.jeremyrempel.gitnotes.settings

import com.github.jeremyrempel.gitnotes.Constants
import com.github.jeremyrempel.gitnotes.api.RepoInfo
import com.github.jeremyrempel.gitnotes.navigation.NavScreen
import com.github.jeremyrempel.gitnotes.repo.SettingsRepo

class SettingsPresenter(private val settingsRepo: SettingsRepo) {

    var repoInfo: RepoInfo? = null
        set(value) {
            if (value != null) dataCallback?.invoke(value)
        }
    var dataCallback: ((RepoInfo) -> Unit)? = null
    var navCallback: ((NavScreen) -> Unit)? = null

    fun onSave(repo: RepoInfo) {
        settingsRepo.save(repo)
        repoInfo = repo

        val navRequest = NavScreen.List("/", false)
        navCallback?.invoke(navRequest)
    }

    fun onReset() {
        val newRepoInfo = RepoInfo(Constants.DEFAULT_REPO_USERNAME, Constants.DEFAULT_REPO_NAME)
        settingsRepo.save(newRepoInfo)
        repoInfo = newRepoInfo
    }

    /**
     * This is wasteful if settingsRepo was a remote source, could retain copy and avoid
     *
     * On the other hand:
     * There are only two hard things in Computer Science: cache invalidation and naming things.
     * -- Phil Karlton
     */
    fun requestData() {
        repoInfo = settingsRepo.get()
    }
}