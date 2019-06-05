package com.github.jeremyrempel.gitnotes.android.di

import com.github.jeremyrempel.gitnotes.api.GithubApi
import com.github.jeremyrempel.gitnotes.api.GithubService
import com.github.jeremyrempel.gitnotes.api.RepoInfo
import com.github.jeremyrempel.gitnotes.presentation.ContentsActions
import com.github.jeremyrempel.gitnotes.presentation.ContentsPresenter
import com.github.jeremyrempel.gitnotes.presentation.ContentsView
import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
class ServiceModule {

    @Provides
    @Singleton
    fun providesHttp() = HttpClient()

    @Provides
    @Singleton
    @Named("APIURL")
    fun providesApiUrl() = "https://api.github.com"

    @Provides
    @Singleton
    fun providesGitHubApi(client: HttpClient, @Named("APIURL") apiUrl: String): GithubApi {
        return GithubService(client, apiUrl)
    }

    @Provides
    @Singleton
    fun providesContext(): CoroutineContext = Dispatchers.Main
}