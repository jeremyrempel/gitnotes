package com.github.jeremyrempel.gitnotes.api

import com.github.jeremyrempel.gitnotes.presentation.ContentsActions
import com.github.jeremyrempel.gitnotes.presentation.ContentsPresenter
import com.github.jeremyrempel.gitnotes.presentation.ContentsView
import io.ktor.client.HttpClient
import kotlin.coroutines.CoroutineContext

fun getApiUrl() = "https://api.github.com"
fun getHttpClient() = HttpClient()

fun getService(
    client: HttpClient = getHttpClient(),
    apiUrl: String = getApiUrl()
): GithubApi {
    return GithubService(client, apiUrl)
}

fun getActions(
    coroutineContext: CoroutineContext,
    view: ContentsView,
    service: GithubApi,
    repo: RepoInfo
): ContentsActions {
    val presenter = ContentsPresenter(coroutineContext, service, repo)
    presenter.view = view
    return presenter
}
