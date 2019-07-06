package com.github.jeremyrempel.gitnotes.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.github.jeremyrempel.gitnotes.android.di.AppComponent
import com.github.jeremyrempel.gitnotes.android.di.DaggerAppComponent
import com.github.jeremyrempel.gitnotes.android.ui.ContentsListFragment
import com.github.jeremyrempel.gitnotes.navigation.NavScreen
import com.github.jeremyrempel.gitnotes.navigation.NavScreen.List

class MainActivity : AppCompatActivity(), NavigationCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        // init dagger graph
        val dagger: AppComponent = DaggerAppComponent.create()

        supportFragmentManager.fragmentFactory = dagger.fragFactory()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame, buildContentsListFragment())
                .commitNow()
        }
    }

    override fun navigateTo(screen: NavScreen) {
        val frag = when (screen) {
            is List -> {
                val frag = buildContentsListFragment()

                Bundle().apply {
                    putString(ContentsListFragment.ARG_PATH, screen.path)
                    frag.arguments = this
                }

                frag
            }
        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame, frag)
            .addToBackStack(null)
            .commit()
    }

    private fun buildContentsListFragment(): Fragment {
        return supportFragmentManager
            .fragmentFactory
            .instantiate(classLoader, ContentsListFragment::class.java.canonicalName)
    }
}

interface NavigationCallback {
    fun navigateTo(screen: NavScreen)
}