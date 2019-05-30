package com.github.jeremyrempel.gitnotes.presentation

import com.github.jeremyrempel.gitnotes.api.GithubApi
import com.github.jeremyrempel.gitnotes.api.RepoInfo
import com.github.jeremyrempel.gitnotes.api.data.ContentsResponse
import com.github.jeremyrempel.gitnotes.api.data.ContentsResponseRow
import com.github.jeremyrempel.gitnotes.navigation.NavScreen
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ContentsPresenter(
    uiContext: CoroutineContext,
    private val view: ContentsView,
    private val api: GithubApi,
    private val repoInfo: RepoInfo
) : CoroutinePresenter(uiContext, view), ContentsActions {

    override fun onSelectItem(item: ContentsResponseRow) {
        view.navigateTo(NavScreen.List(item.path))
    }

    /**
     * Send data from cache or request from network
     */
    override fun onRequestData(path: String?) = updateData(path)

    private fun updateData(path: String? = null) {
        view.isUpdating = true

        launch(coroutineContext) {

            when (val response = api.getContents(repoInfo, path)) {
                is ContentsResponse.ListResponse -> view.onUpdate(response.data.sortedBy { it.name })
                is ContentsResponse.ObjectResponse -> view.onUpdate(response.data)
            }

        }.invokeOnCompletion {
            view.isUpdating = false
        }
    }
}