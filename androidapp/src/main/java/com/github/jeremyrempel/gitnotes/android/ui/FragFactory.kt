package com.github.jeremyrempel.gitnotes.android.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject
import javax.inject.Provider

class FragFactory @Inject constructor(
    private val listFragProvider: Provider<ContentsListFragment>
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            ContentsListFragment::class.java.canonicalName -> listFragProvider.get()
            else -> TODO("Missing fragment $className")
        }
    }
}