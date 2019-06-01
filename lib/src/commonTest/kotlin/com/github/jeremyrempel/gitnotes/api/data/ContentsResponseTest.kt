package com.github.jeremyrempel.gitnotes.api.data

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.list
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ContentsResponseTest {

    //language=JSON
    private val testJsonArray = """[
  {
    "name": "README.md",
    "path": "README.md",
    "sha": "dab532ce9d344a8525544aa886b712dff9f97cd6",
    "size": 46,
    "url": "https://api.github.com/repos/jeremyrempel/gitnotestest/contents/README.md?ref=master",
    "html_url": "https://github.com/jeremyrempel/gitnotestest/blob/master/README.md",
    "git_url": "https://api.github.com/repos/jeremyrempel/gitnotestest/git/blobs/dab532ce9d344a8525544aa886b712dff9f97cd6",
    "download_url": "https://raw.githubusercontent.com/jeremyrempel/gitnotestest/master/README.md",
    "type": "file",
    "_links": {
      "self": "https://api.github.com/repos/jeremyrempel/gitnotestest/contents/README.md?ref=master",
      "git": "https://api.github.com/repos/jeremyrempel/gitnotestest/git/blobs/dab532ce9d344a8525544aa886b712dff9f97cd6",
      "html": "https://github.com/jeremyrempel/gitnotestest/blob/master/README.md"
    }
  },
  {
    "name": "shopping",
    "path": "shopping",
    "sha": "a853c17e317f6b1ff0aa09d4bb4b86c7a44261bc",
    "size": 0,
    "url": "https://api.github.com/repos/jeremyrempel/gitnotestest/contents/shopping?ref=master",
    "html_url": "https://github.com/jeremyrempel/gitnotestest/tree/master/shopping",
    "git_url": "https://api.github.com/repos/jeremyrempel/gitnotestest/git/trees/a853c17e317f6b1ff0aa09d4bb4b86c7a44261bc",
    "download_url": null,
    "type": "dir",
    "_links": {
      "self": "https://api.github.com/repos/jeremyrempel/gitnotestest/contents/shopping?ref=master",
      "git": "https://api.github.com/repos/jeremyrempel/gitnotestest/git/trees/a853c17e317f6b1ff0aa09d4bb4b86c7a44261bc",
      "html": "https://github.com/jeremyrempel/gitnotestest/tree/master/shopping"
    }
  }
]""".trimIndent()

    //language=JSON
    private val testJsonSingle = """{
  "name": "README.md",
  "path": "README.md",
  "sha": "dab532ce9d344a8525544aa886b712dff9f97cd6",
  "size": 46,
  "url": "https://api.github.com/repos/jeremyrempel/gitnotestest/contents/README.md?ref=master",
  "html_url": "https://github.com/jeremyrempel/gitnotestest/blob/master/README.md",
  "git_url": "https://api.github.com/repos/jeremyrempel/gitnotestest/git/blobs/dab532ce9d344a8525544aa886b712dff9f97cd6",
  "download_url": "https://raw.githubusercontent.com/jeremyrempel/gitnotestest/master/README.md",
  "type": "file",
  "content": "IyBnaXRub3Rlc3Rlc3QKVGVzdCBSZXBvIGZvciBHaXROb3RlcyBwcm9qZWN0\nCg==\n",
  "encoding": "base64",
  "_links": {
    "self": "https://api.github.com/repos/jeremyrempel/gitnotestest/contents/README.md?ref=master",
    "git": "https://api.github.com/repos/jeremyrempel/gitnotestest/git/blobs/dab532ce9d344a8525544aa886b712dff9f97cd6",
    "html": "https://github.com/jeremyrempel/gitnotestest/blob/master/README.md"
  }
}""".trimIndent()

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