package com.github.jeremyrempel.gitnotes.api

import com.github.jeremyrempel.gitnotes.presentation.ContentsActions
import com.github.jeremyrempel.gitnotes.presentation.ContentsPresenter
import com.github.jeremyrempel.gitnotes.presentation.ContentsView
import kotlin.coroutines.CoroutineContext

fun getActions(
    coroutineContext: CoroutineContext,
    view: ContentsView,
    service: GithubApi
): ContentsActions {
    return ContentsPresenter(coroutineContext, view, service)
}
