package com.github.jeremyrempel.gitnotes.api.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class ContentsResponseTest {

    @Serializable
    data class Hello(val name: String)

    //language=JSON
    private val testString = "{\n  \"name\": \"World\"\n}".trimIndent()

    private val testData = Hello("World")

    @Test
    fun `test basic json serialize`() {
        val testResult = Json.parse(Hello.serializer(), testString)
        assertEquals(testData, testResult)
    }

    @Test
    fun `test basic json deserialize`() {
        val testResult = Json.stringify(Hello.serializer(), testData)
        assertEquals("{\"name\":\"World\"}", testResult)
    }

}