package com.github.jeremyrempel.gitnotes.android.repo

import android.content.Context
import com.github.jeremyrempel.gitnotes.Constants
import com.github.jeremyrempel.gitnotes.api.RepoInfo
import com.github.jeremyrempel.gitnotes.repo.SettingsRepo
import javax.inject.Inject

class SettingsRepoSharedPref @Inject constructor(
    private val context: Context
) : SettingsRepo {

    override fun updateRepoName(username: String, repoName: String) {
        context
            .getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(Constants.REPO_NAME_FIELD, username)
            .putString(Constants.REPO_USERNAME_FIELD, repoName)
            .apply()
    }

    override fun getRepoInfo(): RepoInfo {
        val repoInfo = context
            .getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
            .let {
                RepoInfo(
                    it.getString(Constants.REPO_USERNAME_FIELD, Constants.DEFAULT_REPO_USERNAME)
                        ?: Constants.DEFAULT_REPO_USERNAME,
                    it.getString(Constants.REPO_NAME_FIELD, Constants.DEFAULT_REPO) ?: Constants.DEFAULT_REPO
                )
            }

        return repoInfo
    }
}