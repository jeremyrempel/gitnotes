package com.github.jeremyrempel.gitnotes.android.di

import com.github.jeremyrempel.gitnotes.android.ui.ContentsPresenterModule
import com.github.jeremyrempel.gitnotes.android.factory.FragFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ServiceModule::class, ContentsPresenterModule::class])
interface AppComponent {
    fun fragFactory(): FragFactory
}