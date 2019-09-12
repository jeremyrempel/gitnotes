package com.github.jeremyrempel.gitnotes.api

//language=JSON
val testJsonArray = """[
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
val testJsonSingle = """{
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