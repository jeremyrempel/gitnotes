package com.github.jeremyrempel.gitnotes.android.ui

import com.github.jeremyrempel.gitnotes.api.GithubApi
import com.github.jeremyrempel.gitnotes.api.RepoInfo
import com.github.jeremyrempel.gitnotes.presentation.ContentsPresenter
import dagger.Module
import dagger.Provides
import kotlin.coroutines.CoroutineContext

@Module
class ContentsPresenterModule {

    // todo refactor this
    private val repoInfo = RepoInfo("jeremyrempel", "gitnotestest")

    @Provides
    fun providesContentsListFragment(
        context: CoroutineContext, api: GithubApi
    ): ContentsListFragment {

        val actions = ContentsPresenter(context, api, repoInfo)
        val frag = ContentsListFragment(actions)
        actions.view = frag

        return frag
    }
}