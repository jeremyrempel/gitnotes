package com.github.jeremyrempel.gitnotes.android.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.jeremyrempel.gitnotes.LogLevel
import com.github.jeremyrempel.gitnotes.android.ui.ContentsUi
import com.github.jeremyrempel.gitnotes.android.util.SingleLiveEvent
import com.github.jeremyrempel.gitnotes.api.data.ContentsResponseRow
import com.github.jeremyrempel.gitnotes.log
import com.github.jeremyrempel.gitnotes.navigation.NavScreen
import com.github.jeremyrempel.gitnotes.presentation.ContentsActions
import com.github.jeremyrempel.gitnotes.presentation.ContentsView
import javax.inject.Inject

class ContentsViewModel @Inject constructor(
    private val actions: ContentsActions
) : ContentsView, ViewModel() {

    private val dataList = MutableLiveData<ContentsUi>()
    private val showLoading = MutableLiveData<Boolean>()
    private val showError = MutableLiveData<String>()
    private val navEvent = SingleLiveEvent<NavScreen>()

    // todo add a check, don't request data if already requested
    fun requestData(path: String?) = actions.onRequestData(path)

    fun onSelectItem(item: ContentsResponseRow) = actions.onSelectItem(item)

    override fun onUpdate(responseData: List<ContentsResponseRow>) {
        dataList.value = ContentsUi.Multiple(responseData)
    }

    override fun onUpdate(responseData: ContentsResponseRow) {
        dataList.value = ContentsUi.Single(responseData)
    }

    override fun navigateTo(screen: NavScreen) {
        navEvent.value = screen
    }

    override var isUpdating: Boolean
        get() = false
        set(value) {
            showLoading.value = value
        }

    override fun onError(error: Throwable) {
        log(LogLevel.ERROR, TAG, "Get content error", error)
        showError.value = error.message
    }

    fun getData(): LiveData<ContentsUi> = dataList
    fun isLoading(): LiveData<Boolean> = showLoading
    fun isError(): LiveData<String> = showError
    fun navEvent(): LiveData<NavScreen> = navEvent

    companion object {
        const val TAG = "ContentsViewModel"
    }
}