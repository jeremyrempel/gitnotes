package com.github.jeremyrempel.gitnotes.api

import com.github.jeremyrempel.gitnotes.api.data.ContentsResponse
import com.github.jeremyrempel.gitnotes.api.data.ContentsResponseRow
import com.github.jeremyrempel.gitnotes.api.data.ReadMeResponse
import com.github.jeremyrempel.gitnotes.decodeBase64
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.http.takeFrom
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.list

@UnstableDefault
class GithubService constructor(
    private val client: HttpClient,
    private val endPoint: String,
    private val jsonParser: Json = Json.nonstrict
) :
    GithubApi {

    override suspend fun getContents(repoInfo: RepoInfo, path: String?): ContentsResponse {
        val newPath = "repos/${repoInfo.user}/${repoInfo.repo}/contents".let {
            if (path != null) "$it/$path" else it
        }

        val stringResponse = client.get<String> {
            apiUrl(newPath)
        }

        // parsing json twice :(
        // find a better way to determine if array or object
        return when (Json.nonstrict.parseJson(stringResponse)) {
            is JsonObject -> {
                val response = jsonParser.parse(ContentsResponseRow.serializer(), stringResponse)

                if (response.content != null) {
                    // todo this sucks. use a custom serializer
                    val responseText = decodeBase64(response.content)
                    ContentsResponse.ObjectResponse(response.copy(content = responseText))
                } else {
                    ContentsResponse.ObjectResponse(response)
                }
            }
            is JsonArray -> ContentsResponse.ListResponse(
                jsonParser.parse(
                    ContentsResponseRow.serializer().list,
                    stringResponse
                )
            )
            else -> ContentsResponse.UnknownResponse
        }
    }

    override suspend fun getReadme(): ReadMeResponse {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    private fun HttpRequestBuilder.apiUrl(path: String) = url {
        takeFrom(endPoint)
        encodedPath = path
    }
}
