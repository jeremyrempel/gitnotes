package com.github.jeremyrempel.gitnotes.android.di

import com.github.jeremyrempel.gitnotes.android.factory.FactoryModule
import com.github.jeremyrempel.gitnotes.android.factory.FragFactory
import com.github.jeremyrempel.gitnotes.android.vm.ContentsViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ServiceModule::class,
        ContentsViewModelModule::class,
        FactoryModule::class
    ]
)
interface AppComponent {
    fun fragFactory(): FragFactory
}