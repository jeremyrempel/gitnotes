package com.github.jeremyrempel.gitnotes.presentation

import com.github.jeremyrempel.gitnotes.api.data.ContentsResponse

interface ContentsView : BaseView {
    fun onUpdate(data: List<ContentsResponse>)
}

interface ContentsActions {
    fun onRequestData()
}