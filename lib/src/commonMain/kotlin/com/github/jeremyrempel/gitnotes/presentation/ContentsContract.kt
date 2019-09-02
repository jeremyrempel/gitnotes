package com.github.jeremyrempel.gitnotes.presentation

import com.github.jeremyrempel.gitnotes.api.data.ContentsResponseRow
import com.github.jeremyrempel.gitnotes.navigation.NavScreen

interface ContentsView : BaseView {
    fun onUpdate(responseData: List<ContentsResponseRow>)
    fun onUpdate(responseData: ContentsResponseRow)
    fun navigateTo(screen: NavScreen)
}

interface ContentsActions {
    fun onRequestData(path: String? = null)
    fun onSelectItem(item: ContentsResponseRow)
}