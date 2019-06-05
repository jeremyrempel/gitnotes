package com.github.jeremyrempel.gitnotes.android.ui

import com.github.jeremyrempel.gitnotes.api.GithubApi
import com.github.jeremyrempel.gitnotes.api.RepoInfo
import com.github.jeremyrempel.gitnotes.presentation.ContentsActions
import com.github.jeremyrempel.gitnotes.presentation.ContentsPresenter
import com.github.jeremyrempel.gitnotes.presentation.ContentsView
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
class ContentsPresenterModule(private val view: ContentsView, private val repoInfo: RepoInfo) {

    @Provides
    @Singleton
    fun providesPresenter(context: CoroutineContext, api: GithubApi): ContentsActions {
        return ContentsPresenter(context, view, api, repoInfo)
    }
}