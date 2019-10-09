package com.github.jeremyrempel.gitnotes.android.settings

import android.content.Context
import com.github.jeremyrempel.gitnotes.repo.SettingsRepo
import com.github.jeremyrempel.gitnotes.settings.SettingsPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SettingsModule {

    @Provides
    @Singleton
    fun providesSettingsRepo(context: Context): SettingsRepo {
        return SettingsRepoSharedPref(context)
    }

    @Provides
    fun presenter(repo: SettingsRepo): SettingsPresenter {
        return SettingsPresenter(repo)
    }
}