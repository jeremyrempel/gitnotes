package com.github.jeremyrempel.gitnotes.android.factory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.jeremyrempel.gitnotes.android.ui.AboutFragment
import com.github.jeremyrempel.gitnotes.android.ui.ContentsListFragment
import com.github.jeremyrempel.gitnotes.android.ui.SettingsFragment
import javax.inject.Inject
import javax.inject.Provider

class FragFactory @Inject constructor(
    private val listFragProvider: Provider<ContentsListFragment>
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            ContentsListFragment::class.java.canonicalName -> listFragProvider.get()
            AboutFragment::class.java.canonicalName -> AboutFragment()
            SettingsFragment::class.java.canonicalName -> SettingsFragment()
            else -> TODO("Missing fragment $className")
        }
    }
}
