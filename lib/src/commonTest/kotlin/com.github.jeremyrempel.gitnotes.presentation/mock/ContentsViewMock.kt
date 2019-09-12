package com.github.jeremyrempel.gitnotes.presentation

import com.github.jeremyrempel.gitnotes.api.data.ContentsResponseRow
import com.github.jeremyrempel.gitnotes.navigation.NavScreen
import kotlin.properties.Delegates

class ContentsViewMock : ContentsView {

    val isUpdatingHistory = mutableListOf<Boolean>()
    var lastResponse: List<ContentsResponseRow>? = null

    override var isUpdating: Boolean by Delegates.observable(false) { _, _, value ->
        isUpdatingHistory.add(
            value
        )
    }

    override fun onUpdate(responseData: List<ContentsResponseRow>) {
        lastResponse = responseData
    }

    override fun onUpdate(responseData: ContentsResponseRow) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun navigateTo(screen: NavScreen) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError(error: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}