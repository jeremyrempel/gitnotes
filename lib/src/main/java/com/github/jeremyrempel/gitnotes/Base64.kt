package com.github.jeremyrempel.gitnotes

import android.util.Base64

actual fun decodeBase64(input: String): String {
    return String(Base64.decode(input, Base64.DEFAULT))
}