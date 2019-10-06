package com.github.jeremyrempel.gitnotes.presentation

import com.github.jeremyrempel.gitnotes.api.GithubApi
import com.github.jeremyrempel.gitnotes.api.data.ContentsResponse
import com.github.jeremyrempel.gitnotes.api.data.ContentsResponseRow
import com.github.jeremyrempel.gitnotes.navigation.NavScreen
import com.github.jeremyrempel.gitnotes.repo.SettingsRepo
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ContentsPresenter(
    uiContext: CoroutineContext,
    private val api: GithubApi,
    private val settingsRepo: SettingsRepo
) : CoroutinePresenter(uiContext), ContentsActions {

    // todo figure out way to inject in constructor
    lateinit var view: ContentsView

    override fun onSelectItem(item: ContentsResponseRow) {
        view.navigateTo(NavScreen.List(item.path, true))
    }

    /**
     * Send data from cache or request from network
     */
    override fun onRequestData(path: String?) = updateData(path)

    private fun updateData(path: String? = null) {
        view.isUpdating = true

        val repoInfo = settingsRepo.getRepoInfo()

        launch(coroutineContext) {
            when (val response = api.getContents(repoInfo, path)) {
                is ContentsResponse.ListResponse -> view.onUpdate(response.data.sortedBy { it.name })
                is ContentsResponse.ObjectResponse -> view.onUpdate(response.data)
            }
        }.invokeOnCompletion {
            view.isUpdating = false
        }
    }

    override fun onError(error: Throwable) = view.onError(error)
}