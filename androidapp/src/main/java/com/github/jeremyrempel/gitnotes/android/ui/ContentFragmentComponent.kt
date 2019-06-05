package com.github.jeremyrempel.gitnotes.android.ui

import com.github.jeremyrempel.gitnotes.android.di.ServiceModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ServiceModule::class, ContentsPresenterModule::class])
@Singleton
interface ContentFragmentComponent {
    fun inject(frag: ContentsListFragment)
}