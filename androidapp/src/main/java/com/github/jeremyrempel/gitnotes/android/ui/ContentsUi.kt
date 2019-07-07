package com.github.jeremyrempel.gitnotes.android.ui

import com.github.jeremyrempel.gitnotes.api.data.ContentsResponseRow

sealed class ContentsUi {
    class Single(val result: ContentsResponseRow) : ContentsUi()
    class Multiple(val result: List<ContentsResponseRow>) : ContentsUi()
}