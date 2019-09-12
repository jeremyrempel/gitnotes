package com.github.jeremyrempel.gitnotes.presentation

import com.github.jeremyrempel.gitnotes.api.Fakes
import com.github.jeremyrempel.gitnotes.api.GithubApiFake
import com.github.jeremyrempel.gitnotes.presentation.mock.ContentsViewMock
import com.github.jeremyrempel.gitnotes.runBlockingTest
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.test.Test
import kotlin.test.assertEquals

class ContentsPresenterTest {

    @Test
    fun `test presenter test `() {

        // setup
        val api = GithubApiFake()
        val presenter = ContentsPresenter(EmptyCoroutineContext, api, Fakes.Repo)
        val viewMock = ContentsViewMock()
        presenter.view = viewMock

        // test
        runBlockingTest {
            presenter.onRequestData("")
        }

        // verify
        assertEquals(listOf(true, false), viewMock.isUpdatingHistory)
        assertEquals(api.data.data, viewMock.lastResponse)
    }
}
