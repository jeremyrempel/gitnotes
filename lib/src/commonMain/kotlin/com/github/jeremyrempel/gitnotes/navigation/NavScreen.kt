package com.github.jeremyrempel.gitnotes.navigation

sealed class NavScreen(val showBackButton: Boolean) {
    class List(val path: String?, showBackButton: Boolean) : NavScreen(showBackButton)
    class Settings(showBackButton: Boolean = true) : NavScreen(showBackButton)
    class About(showBackButton: Boolean = true) : NavScreen(showBackButton)
}