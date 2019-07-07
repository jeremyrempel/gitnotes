package com.github.jeremyrempel.gitnotes.android.vm

import androidx.lifecycle.ViewModel
import com.github.jeremyrempel.gitnotes.api.data.ContentsResponseRow
import com.github.jeremyrempel.gitnotes.navigation.NavScreen
import com.github.jeremyrempel.gitnotes.presentation.ContentsActions
import com.github.jeremyrempel.gitnotes.presentation.ContentsView
import javax.inject.Inject

class ContentsViewModel @Inject constructor(
    private val actions: ContentsActions
) : ContentsView, ViewModel() {

    fun requestData(path: String?) {
        actions.onRequestData(path)
    }

    fun onSelectItem(item: ContentsResponseRow) {
        actions.onSelectItem(item)
    }

    override fun onUpdate(data: List<ContentsResponseRow>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUpdate(data: ContentsResponseRow) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun navigateTo(screen: NavScreen) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override var isUpdating: Boolean
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}

    override fun onError(error: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}