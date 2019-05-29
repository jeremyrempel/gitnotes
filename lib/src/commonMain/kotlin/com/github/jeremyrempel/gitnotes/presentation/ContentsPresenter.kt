package com.github.jeremyrempel.gitnotes.presentation

import com.github.jeremyrempel.gitnotes.api.GithubApi
import com.github.jeremyrempel.gitnotes.api.RepoInfo
import com.github.jeremyrempel.gitnotes.api.data.ContentsResponse
import com.github.jeremyrempel.gitnotes.navigation.NavScreen
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ContentsPresenter(
    uiContext: CoroutineContext,
    private val view: ContentsView,
    private val api: GithubApi,
    private val repoInfo: RepoInfo
) : CoroutinePresenter(uiContext, view), ContentsActions {

    override fun onSelectItem(item: ContentsResponse) {
        view.navigateTo(NavScreen.List(item.path))
    }

    /**
     * Send data from cache or request from network
     */
    override fun onRequestData(path: String?) = updateData(path)

    private fun updateData(path: String? = null) {
        view.isUpdating = true

        launch(coroutineContext) {
            val response = api.getContents(repoInfo, path).sortedBy { it.name }
            view.onUpdate(response)
        }.invokeOnCompletion {
            view.isUpdating = false
        }
    }
}