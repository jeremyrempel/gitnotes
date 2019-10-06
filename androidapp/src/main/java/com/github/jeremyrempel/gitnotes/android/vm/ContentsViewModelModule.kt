package com.github.jeremyrempel.gitnotes.android.vm

import com.github.jeremyrempel.gitnotes.api.GithubApi
import com.github.jeremyrempel.gitnotes.presentation.ContentsPresenter
import com.github.jeremyrempel.gitnotes.repo.SettingsRepo
import dagger.Module
import dagger.Provides
import kotlin.coroutines.CoroutineContext

@Module
class ContentsViewModelModule {

    @Provides
    fun providesContentsViewModel(
        context: CoroutineContext,
        api: GithubApi,
        settingsRepo: SettingsRepo
    ): ContentsViewModel {

        val actions = ContentsPresenter(context, api, settingsRepo)
        val vm = ContentsViewModel(actions)
        actions.view = vm

        return vm
    }
}
