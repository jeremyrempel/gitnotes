package com.github.jeremyrempel.gitnotes.android.di

import com.github.jeremyrempel.gitnotes.android.ui.ContentsListFragment
import com.github.jeremyrempel.gitnotes.api.GithubApi
import dagger.Component
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Component(modules = [ServiceModule::class])
@Singleton
interface SingletonComponent {
    fun gitHubApi(): GithubApi
    fun coroutineContext(): CoroutineContext
    fun inject(frag: ContentsListFragment): ContentsListFragment
}