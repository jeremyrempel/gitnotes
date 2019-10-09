package com.github.jeremyrempel.gitnotes.api.data

import com.github.jeremyrempel.gitnotes.api.mock.Fakes
import com.github.jeremyrempel.gitnotes.api.GithubService
import com.github.jeremyrempel.gitnotes.runBlockingTest
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.list
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.fail

class GithubServiceTest {

    private val endPoint = "http://localhost"

    @UnstableDefault
    @Test
    fun `testing ktor service request and response success`() {

        val resultData = listOf(Fakes.ContentsResponse)
        val resultJson = Json.stringify(ContentsResponseRow.serializer().list, listOf(Fakes.ContentsResponse))

        // https://api.github.com/repos/jeremyrempel/gitnotestest/contents/
        val client = HttpClient(MockEngine) {
            engine {
                addHandler {
                    respond(resultJson)
                }
            }
        }

        runBlockingTest {
            when (val testResult = GithubService(client, endPoint).getContents(Fakes.Repo)) {
                is ContentsResponse.ListResponse -> assertEquals(resultData, testResult.data)
                else -> fail("Invalid response")
            }
        }
    }

    @Test
    fun `test failure throws exception`() {
        assertFails {
            val client = HttpClient(MockEngine) {
                engine {
                    addHandler {
                        error("fail")
                    }
                }
            }

            runBlockingTest {
                GithubService(client, endPoint).getContents(Fakes.Repo)
            }
        }
    }
}