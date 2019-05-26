package com.github.jeremyrempel.gitnotes.presentation

import com.github.jeremyrempel.gitnotes.api.data.ReadMeResponse

interface ReadmeView : BaseView {
    fun onUpdate(data: ReadMeResponse)
}

interface ReadmeActions {
    fun onRequestData()
}