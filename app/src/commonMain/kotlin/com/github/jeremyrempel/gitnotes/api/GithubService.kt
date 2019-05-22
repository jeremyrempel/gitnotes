package com.github.jeremyrempel.gitnotes.api

import com.github.jeremyrempel.gitnotes.api.data.ContentsResponse
import com.github.jeremyrempel.gitnotes.api.data.ReadMeResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.http.takeFrom
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

class GithubService(
    private val client: HttpClient,
    private val endPoint: String,
    private val user: String,
    private val repo: String
) :
    GithubApi {

    override suspend fun getContents(): List<ContentsResponse> {
        val stringResponse = client.get<String> { apiUrl("repos/$user/$repo/contents") }
        val serializer = getSerializer()

        return Json.nonstrict.parse(serializer, stringResponse)
    }

    private fun getSerializer() = ContentsResponse.serializer().list

    override suspend fun getReadme(): ReadMeResponse = client.get {
        apiUrl("repos/$user/$repo/readme")
    }

    private fun HttpRequestBuilder.apiUrl(path: String) {
        url {
            takeFrom(endPoint)
            encodedPath = path
        }
    }
}