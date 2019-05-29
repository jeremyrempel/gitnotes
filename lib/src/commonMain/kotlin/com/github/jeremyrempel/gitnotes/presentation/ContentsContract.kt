package com.github.jeremyrempel.gitnotes.presentation

import com.github.jeremyrempel.gitnotes.api.data.ContentsResponse
import com.github.jeremyrempel.gitnotes.navigation.NavScreen

interface ContentsView : BaseView {
    fun onUpdate(data: List<ContentsResponse>)
    fun navigateTo(screen: NavScreen)
}

interface ContentsActions {
    fun onRequestData(path: String? = null)
    fun onSelectItem(item: ContentsResponse)
}