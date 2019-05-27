package com.github.jeremyrempel.gitnotes.presentation

import com.github.jeremyrempel.gitnotes.api.data.ContentsResponse

interface ContentsView : BaseView {
    fun onUpdate(data: List<ContentsResponse>)
    fun navigateTo()
}

interface ContentsActions {
    fun onRequestData()
    fun onClick(item: ContentsResponse)
}