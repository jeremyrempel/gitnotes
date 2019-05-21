package com.github.jeremyrempel.gitnotes.presentation

import com.github.jeremyrempel.gitnotes.api.GithubApi
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ContentsPresenter(
    uiContext: CoroutineContext,
    private val view: ContentsView,
    private val api: GithubApi
) : CoroutinePresenter(uiContext, view), ContentsActions {

    override fun onRequestData() = updateData()

    private fun updateData() {
        view.isUpdating = true

        launch(coroutineContext) {
            val response = api.getContents()
            view.onUpdate(response)
        }.invokeOnCompletion {
            view.isUpdating = false
        }
    }
}