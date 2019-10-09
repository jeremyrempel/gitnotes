package com.github.jeremyrempel.gitnotes.android.di

import android.content.Context
import com.github.jeremyrempel.gitnotes.android.factory.FactoryModule
import com.github.jeremyrempel.gitnotes.android.factory.FragFactory
import com.github.jeremyrempel.gitnotes.android.settings.SettingsModule
import com.github.jeremyrempel.gitnotes.android.vm.ContentsViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ServiceModule::class,
        FactoryModule::class,
        ContentsViewModelModule::class,
        SettingsModule::class
    ]
)
interface AppComponent {
    fun fragFactory(): FragFactory
    fun context(context: Context): Context
}
