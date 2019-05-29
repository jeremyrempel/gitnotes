package com.github.jeremyrempel.gitnotes.api

import com.github.jeremyrempel.gitnotes.api.data.ContentsResponse
import com.github.jeremyrempel.gitnotes.api.data.ReadMeResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.http.takeFrom
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

class GithubService(
    private val client: HttpClient,
    private val endPoint: String,
    private val serializer: KSerializer<List<ContentsResponse>> = ContentsResponse.serializer().list,
    private val jsonParser: Json = Json.nonstrict
) :
    GithubApi {


    override suspend fun getContents(repo: RepoInfo, path: String?): List<ContentsResponse> {
        val stringResponse = client.get<String> { apiUrl("repos/${repo.user}/${repo.repo}/contents") }

        return jsonParser.parse(serializer, stringResponse)
    }

    override suspend fun getReadme(): ReadMeResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun HttpRequestBuilder.apiUrl(path: String) = url {
        takeFrom(endPoint)
        encodedPath = path
    }
}
