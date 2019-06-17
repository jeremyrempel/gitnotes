package com.github.jeremyrempel.gitnotes.android.ui

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Test
    fun `test main activity launches`() {
        launch(MainActivity::class.java)
    }
}