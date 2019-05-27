package com.github.jeremyrempel.gitnotes.api.data

import com.github.jeremyrempel.gitnotes.api.GithubService
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import kotlinx.serialization.json.Json
import kotlinx.serialization.list
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.runBlocking
import kotlin.test.assertFails

class GithubServiceTest {

    val endPoint = "http://localhost"
    val user = "testuser"
    val repo = "testrepo"

    @Test
    fun `testing ktor service request and response success`() {

        val resultData = listOf(
            ContentsResponse("name", "type", 100, "http://github.com/blah")
        )
        val resultJson = Json.stringify(ContentsResponse.serializer().list, resultData)

        // https://api.github.com/repos/jeremyrempel/gitnotestest/contents/
        val client = HttpClient(MockEngine) {
            engine {
                addHandler {
                    respond(resultJson)
                }
            }
        }

        runBlocking {
            val testResult = GithubService(client, endPoint, user, repo).getContents()
            assertEquals(resultData, testResult)
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

            runBlocking {
                GithubService(client, endPoint, user, repo).getContents()
            }
        }
    }
}