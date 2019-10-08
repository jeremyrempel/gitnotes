package com.github.jeremyrempel.gitnotes

import android.util.Log
import com.github.jeremyrempel.gitnotes.android.lib.BuildConfig

actual fun log(level: LogLevel, tag: String, message: String, error: Throwable) {
    if (!BuildConfig.DEBUG) {
        return
    }

    when (level) {
        is LogLevel.DEBUG -> Log.d(tag, message, error)
        is LogLevel.INFO -> Log.i(tag, message, error)
        is LogLevel.WARN -> Log.w(tag, message, error)
        is LogLevel.ERROR -> Log.e(tag, message, error)
    }
}

actual fun log(level: LogLevel, tag: String, message: String) {
    if (!BuildConfig.DEBUG) {
        return
    }

    when (level) {
        is LogLevel.DEBUG -> Log.d(tag, message)
        is LogLevel.INFO -> Log.i(tag, message)
        is LogLevel.WARN -> Log.w(tag, message)
        is LogLevel.ERROR -> Log.e(tag, message)
    }
}
