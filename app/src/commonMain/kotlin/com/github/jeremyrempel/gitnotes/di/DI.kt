package com.github.jeremyrempel.gitnotes.api

import com.github.jeremyrempel.gitnotes.presentation.ContentsActions
import com.github.jeremyrempel.gitnotes.presentation.ContentsPresenter
import com.github.jeremyrempel.gitnotes.presentation.ContentsView
import io.ktor.client.HttpClient
import io.ktor.http.Url
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext


fun getApiUrl() = "https://api.github.com"
fun getHttpClient() = HttpClient()

fun getService(
    user: String,
    repo: String,
    client: HttpClient = getHttpClient(),
    apiUrl: String = getApiUrl()
): GithubApi {
    return GithubService(client, apiUrl, user, repo)
}

fun getActions(
    coroutineContext: CoroutineContext,
    view: ContentsView,
    service: GithubApi
): ContentsActions {
    return ContentsPresenter(coroutineContext, view, service)
}
