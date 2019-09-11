package com.github.jeremyrempel.gitnotes.android

import com.github.jeremyrempel.gitnotes.navigation.NavScreen

interface NavigationCallback {
    fun navigateTo(screen: NavScreen)
}
