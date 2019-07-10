package com.github.jeremyrempel.gitnotes.presentation

import com.github.jeremyrempel.gitnotes.LogLevel
import com.github.jeremyrempel.gitnotes.log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class CoroutinePresenter(
    private val mainContext: CoroutineContext // TODO: Use Dispatchers.Main instead when it will be supported on iO
) : CoroutineScope {

    companion object {
        internal val TAG = CoroutinePresenter::class.toString()
    }

    private val job = Job()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        log(LogLevel.ERROR, TAG, "Coroutine Error", throwable)
        onError(throwable)
    }

    abstract fun onError(error: Throwable)

    override val coroutineContext: CoroutineContext
        get() = mainContext + job + exceptionHandler

    open fun onDestroy() {
        job.cancel()
    }
}
