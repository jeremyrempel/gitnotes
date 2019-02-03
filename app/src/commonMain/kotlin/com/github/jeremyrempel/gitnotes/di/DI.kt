package com.github.jeremyrempel.gitnotes.api

import com.github.jeremyrempel.gitnotes.api.data.ReadMeResponse
import io.ktor.client.HttpClient
import io.ktor.client.features.ExpectSuccess
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.JSON

val apiHost = "https://api.github.com"
val httpClient = HttpClient {

    install(JsonFeature) {
        serializer = KotlinxSerializer(JSON.nonstrict).apply {
            setMapper(ReadMeResponse::class, ReadMeResponse.serializer())
        }
    }
    install(ExpectSuccess)
}
