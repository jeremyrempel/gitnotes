package com.github.jeremyrempel.gitnotes.android.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.github.jeremyrempel.gitnotes.android.R
import com.github.jeremyrempel.gitnotes.navigation.NavScreen
import com.github.jeremyrempel.gitnotes.navigation.NavScreen.List

class MainActivity : AppCompatActivity(), NavigationCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = FragFactory()

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
        val fragName = ContentsListFragment::class.java
        return supportFragmentManager.fragmentFactory.instantiate(classLoader, fragName.canonicalName)
    }
}

interface NavigationCallback {
    fun navigateTo(screen: NavScreen)
}