package com.github.jeremyrempel.gitnotes.api.data

import com.github.jeremyrempel.gitnotes.api.GithubService
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.fullPath
import io.ktor.util.InternalAPI
import kotlinx.serialization.json.Json
import kotlinx.serialization.list
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.runBlocking

@InternalAPI
class GithubServiceTest {

    @Test
    fun `testing ktor service request and response success`() {

        val endPoint = "http://localhost"
        val user = "testuser"
        val repo = "testrepo"

        val resultData = listOf(
            ContentsResponse("name", "type", 100, "http://github.com/blah")
        )
        val resultJson = Json.stringify(ContentsResponse.serializer().list, resultData)

        // https://api.github.com/repos/jeremyrempel/gitnotestest/contents/
        val client = HttpClient(MockEngine) {
            engine {
                addHandler { request ->
                    when (request.url.fullPath) {
                        "/repos/$user/$repo/contents" -> respond(resultJson)
                        else -> error("Unhandled ${request.url.fullPath}")
                    }
                }
            }
        }

        runBlocking {
            val testResult = GithubService(client, endPoint, user, repo).getContents()
            assertEquals(resultData, testResult)
        }
    }
}