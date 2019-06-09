package com.github.jeremyrempel.gitnotes.api.data

import com.github.jeremyrempel.gitnotes.api.testJsonArray
import com.github.jeremyrempel.gitnotes.api.testJsonSingle
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.list
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ContentsResponseTest {

    @Test
    fun `test basic json deserialize array`() {
        val testResult = Json.nonstrict.parse(ContentsResponseRow.serializer().list, testJsonArray)
        val expectedResult = listOf(
            ContentsResponseRow(
                "README.md",
                "README.md",
                "file",
                46,
                "https://api.github.com/repos/jeremyrempel/gitnotestest/contents/README.md?ref=master"
            ),
            ContentsResponseRow(
                "shopping",
                "shopping",
                "dir",
                0,
                "https://api.github.com/repos/jeremyrempel/gitnotestest/contents/shopping?ref=master"
            )
        )

        assertEquals(expectedResult, testResult)
    }

    @Test
    fun `test basic json deserialize object`() {
        val testResult = Json.nonstrict.parse(ContentsResponseRow.serializer(), testJsonSingle)
        val expectedResult = ContentsResponseRow(
            "README.md",
            "README.md",
            "file",
            46,
            "https://api.github.com/repos/jeremyrempel/gitnotestest/contents/README.md?ref=master",
            "IyBnaXRub3Rlc3Rlc3QKVGVzdCBSZXBvIGZvciBHaXROb3RlcyBwcm9qZWN0\nCg==\n"
        )

        assertEquals(expectedResult, testResult)
    }

    @Test
    fun `test json object`() {

        val element = Json.nonstrict.parseJson(testJsonSingle)
        assertTrue(element is JsonObject)
        assertFalse(element is JsonArray)
    }

    @Test
    fun `test json array`() {
        val element = Json.nonstrict.parseJson(testJsonArray)
        assertFalse(element is JsonObject)
        assertTrue(element is JsonArray)
    }
}