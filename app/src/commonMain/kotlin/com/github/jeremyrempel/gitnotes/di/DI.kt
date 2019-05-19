package com.github.jeremyrempel.gitnotes.api

import io.ktor.client.HttpClient

val apiHost = "https://api.github.com"
val httpClient = HttpClient {

}

//val httpClient = HttpClient {
//
//    install(JsonFeature) {
//        serializer = KotlinxSerializer(JSON.nonstrict).apply {
//            setMapper(ReadMeResponse::class, ReadMeResponse.serializer())
//            setListMapper(ContentsResponse::class, ContentsResponse.serializer())
//        }
//    }
//    install(ExpectSuccess)
//}
