package com.github.jeremyrempel.gitnotes.android.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory

class FragFactory : FragmentFactory() {

    private val dagger = DaggerFragmentComponent.create()

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            ContentsListFragment::class.java.canonicalName -> dagger.contentsListFrag()
            else -> TODO("Missing fragment $className")
        }
    }
}