package com.github.jeremyrempel.gitnotes.presentation

interface BaseView {
    var isUpdating: Boolean
    fun showError(error: Throwable)
}
