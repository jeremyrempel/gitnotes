package com.github.jeremyrempel.gitnotes.android.factory

import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides

@Module
class FactoryModule {
    @Provides
    fun providesVmFactory(vmFactory: FragVmFactory): ViewModelProvider.Factory = vmFactory

    @Provides
    fun providesFragFactory(fragFactory: FragFactory): FragmentFactory = fragFactory
}