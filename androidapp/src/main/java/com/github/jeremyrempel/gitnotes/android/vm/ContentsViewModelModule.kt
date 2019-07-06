package com.github.jeremyrempel.gitnotes.android.vm

import com.github.jeremyrempel.gitnotes.api.GithubApi
import com.github.jeremyrempel.gitnotes.api.RepoInfo
import com.github.jeremyrempel.gitnotes.presentation.ContentsPresenter
import dagger.Module
import dagger.Provides
import kotlin.coroutines.CoroutineContext

@Module
class ContentsViewModelModule {

    // todo refactor this
    private val repoInfo = RepoInfo("jeremyrempel", "gitnotestest")

    @Provides
    fun providesContentsViewModel(
        context: CoroutineContext,
        api: GithubApi
    ): ContentsViewModel {

        val actions = ContentsPresenter(context, api, repoInfo)
        val vm = ContentsViewModel(actions)
        actions.view = vm

        return vm
    }
}