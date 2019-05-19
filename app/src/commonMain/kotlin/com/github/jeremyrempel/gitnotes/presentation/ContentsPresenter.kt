package com.github.jeremyrempel.gitnotes.presentation

import com.github.jeremyrempel.gitnotes.api.GithubApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ContentsPresenter(
    uiContext: CoroutineContext,
    val view: ContentsView,
    val api: GithubApi
) : CoroutinePresenter(uiContext, view), ContentsActions {

    override fun onRequestData() = updateData()

    private fun updateData() {
        view.isUpdating = true

        GlobalScope.launch(coroutineContext) {
            val response = api.getContents()
            view.onUpdate(response)
        }.invokeOnCompletion {
            view.isUpdating = false
        }
    }
}