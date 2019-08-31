object BuildPlugins {

    object Versions {
        const val kotlinVersion = "1.3.41"
        const val gradleAndroidVersion = "3.6.0-alpha09"
        const val klintVersion = "8.1.0"
    }

    const val androidGradlePlugin =
        "com.android.tools.build:gradle:${Versions.gradleAndroidVersion}"
    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    const val kotlinxSerialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlinVersion}"
    const val kLintPlugin = "org.jlleitschuh.gradle:ktlint-gradle:${Versions.klintVersion}"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"

}

object AndroidSdk {
    const val min = 15
    const val compile = 28
    const val target = compile
}

object Libraries {
    private object Versions {
        const val jetpack = "1.0.0-beta01"
        const val constraintLayout = "1.1.2"
        const val ktx = "1.1.0-alpha05"
    }

    const val kotlinStdLib =
        "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${BuildPlugins.Versions.kotlinVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.jetpack}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val ktxCore = "androidx.core:core-ktx:${Versions.ktx}"
}

object TestLibraries {
    private object Versions {
        const val junit4 = "4.12"
        const val testRunner = "1.1.0-alpha4"
        const val espresso = "3.1.0-alpha4"
    }

    const val junit4 = "junit:junit:${Versions.junit4}"
    const val testRunner = "androidx.test:runner:${Versions.testRunner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}