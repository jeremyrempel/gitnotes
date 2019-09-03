package com.github.jeremyrempel.gitnotes.navigation

sealed class NavScreen {
    class List(val path: String?) : NavScreen()
    object Settings : NavScreen()
    object About : NavScreen()
}