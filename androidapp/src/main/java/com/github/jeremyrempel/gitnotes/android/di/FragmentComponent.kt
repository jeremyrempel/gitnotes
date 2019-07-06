package com.github.jeremyrempel.gitnotes.android.di

import com.github.jeremyrempel.gitnotes.android.ui.ContentsListFragment
import com.github.jeremyrempel.gitnotes.android.ui.ContentsPresenterModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ServiceModule::class, ContentsPresenterModule::class])
@Singleton
interface FragmentComponent {
    fun contentsListFrag(): ContentsListFragment
}