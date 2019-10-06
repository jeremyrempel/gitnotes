package com.github.jeremyrempel.gitnotes.android.di

import android.content.Context
import com.github.jeremyrempel.gitnotes.android.repo.SettingsRepoSharedPref
import com.github.jeremyrempel.gitnotes.api.GithubApi
import com.github.jeremyrempel.gitnotes.api.GithubService
import com.github.jeremyrempel.gitnotes.repo.SettingsRepo
import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
class ServiceModule(private val ctx: Context) {

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
    fun providesSettingsRepo(): SettingsRepo {
        return SettingsRepoSharedPref(ctx)
    }

    @Provides
    @Singleton
    fun providesContext(): CoroutineContext = Dispatchers.Main
}
