package com.github.jeremyrempel.gitnotes.android.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.jeremyrempel.gitnotes.android.R
import com.github.jeremyrempel.gitnotes.navigation.NavScreen
import com.github.jeremyrempel.gitnotes.navigation.NavScreen.List

class MainActivity : AppCompatActivity(), NavigationCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)

        if (savedInstanceState == null) {
            val frag = ContentsListFragment.createInstance()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame, frag)
                .commitNow()
        }
    }

    override fun navigateTo(screen: NavScreen) {
        val frag = when (screen) {
            is List -> ContentsListFragment.createInstance(screen.path)
        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame, frag)
            .addToBackStack(null)
            .commit()
    }
}

interface NavigationCallback {
    fun navigateTo(screen: NavScreen)
}