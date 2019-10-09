package com.github.jeremyrempel.gitnotes.android.settings

import android.content.Context
import com.github.jeremyrempel.gitnotes.Constants
import com.github.jeremyrempel.gitnotes.api.RepoInfo
import com.github.jeremyrempel.gitnotes.repo.SettingsRepo
import javax.inject.Inject

class SettingsRepoSharedPref @Inject constructor(
    private val context: Context
) : SettingsRepo {

    override fun save(repoInfo: RepoInfo) {
        context
            .getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(Constants.REPO_NAME_FIELD, repoInfo.repo)
            .putString(Constants.REPO_USERNAME_FIELD, repoInfo.user)
            .apply()
    }

    override fun get(): RepoInfo {
        return context
            .getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
            .let {
                RepoInfo(
                    it.getString(Constants.REPO_USERNAME_FIELD, Constants.DEFAULT_REPO_USERNAME)
                        ?: Constants.DEFAULT_REPO_USERNAME,
                    it.getString(Constants.REPO_NAME_FIELD, Constants.DEFAULT_REPO_NAME) ?: Constants.DEFAULT_REPO_NAME
                )
            }
    }
}