package com.github.jeremyrempel.gitnotes.api

import com.github.jeremyrempel.gitnotes.api.data.ContentsResponse
import com.github.jeremyrempel.gitnotes.api.data.ReadMeResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.http.takeFrom

class GithubService(
    private val client: HttpClient,
    private val endPoint: String,
    private val user: String,
    private val repo: String
) :
    GithubApi {

    override suspend fun getContents(): List<ContentsResponse> = client.get {
        apiUrl("repos/$user/$repo/contents")
    }

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