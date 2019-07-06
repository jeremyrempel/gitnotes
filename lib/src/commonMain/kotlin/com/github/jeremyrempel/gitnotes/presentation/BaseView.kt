package com.github.jeremyrempel.gitnotes.presentation

interface BaseView {
    var isUpdating: Boolean
    fun onError(error: Throwable)
}
