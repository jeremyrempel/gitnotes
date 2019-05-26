package com.github.jeremyrempel.gitnotes.presentation

import com.github.jeremyrempel.gitnotes.api.GithubApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ReadmePresenter(
    uiContext: CoroutineContext,
    val view: ReadmeView,
    val api: GithubApi
) : CoroutinePresenter(uiContext, view), ReadmeActions {

    override fun onRequestData() = updateData()

    private fun updateData() {
        view.isUpdating = true

        GlobalScope.launch(coroutineContext) {
            val response = api.getReadme()
            view.onUpdate(response)
        }.invokeOnCompletion {
            view.isUpdating = false
        }
    }
}