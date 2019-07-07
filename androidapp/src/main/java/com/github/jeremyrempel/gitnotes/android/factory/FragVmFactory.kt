package com.github.jeremyrempel.gitnotes.android.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.jeremyrempel.gitnotes.android.vm.ContentsViewModel
import javax.inject.Inject
import javax.inject.Provider

class FragVmFactory @Inject constructor(
    private val contentFragVm: Provider<ContentsViewModel>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            ContentsViewModel::class.java -> contentFragVm.get()
            else -> TODO("Missing viewModel $modelClass")
        } as T
    }
}