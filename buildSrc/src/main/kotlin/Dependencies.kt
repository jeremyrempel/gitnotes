import org.gradle.api.artifacts.dsl.DependencyHandler

object BuildPlugins {

    object Versions {
        const val kotlinVersion = "1.3.41"
        const val gradleAndroidVersion = "3.6.0-alpha09"
        const val klintVersion = "8.1.0"
        const val serialization = "0.11.1"
        const val ktor = "1.2.2"
        const val coroutines = "1.3.0-M2"
        const val mockk = "1.9.3"
    }

    const val androidGradlePlugin =
        "com.android.tools.build:gradle:${Versions.gradleAndroidVersion}"
    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    const val kotlinxSerialization =
        "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlinVersion}"
    const val kLintPlugin = "org.jlleitschuh.gradle:ktlint-gradle:${Versions.klintVersion}"
    const val androidApplication = "com.github.jeremyrempel.gitnotes.android"
}

object AndroidSdk {
    const val min = 24
    const val compile = 28
    const val target = compile
}

object Libraries {
    private object Versions {
        const val ktx = "1.2.0-alpha01"
        const val appCompat = "1.1.0-beta01"
        const val dagger = "2.24"
        const val lifecycleExtensions = "2.2.0-alpha02"
        const val drawerLayout = "1.0.0"
        const val navigation = "2.0.0"
        const val constraintLayout = "1.1.3"
    }

    object Android {
        // androidx
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val drawer = "androidx.drawerlayout:drawerlayout:${Versions.drawerLayout}"
        const val lifecycleExt =
            "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtensions}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

        // dagger
        const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
        const val daggerKapt = "com.google.dagger:dagger-compiler:${Versions.dagger}"

        // common impl
        const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${BuildPlugins.Versions.coroutines}"
        const val serialization =
            "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${BuildPlugins.Versions.serialization}"
        const val ktorOkHttp = "io.ktor:ktor-client-okhttp:${BuildPlugins.Versions.ktor}"
        const val ktorJson = "io.ktor:ktor-client-json-jvm:${BuildPlugins.Versions.ktor}"

        // ktx
        const val ktxCore = "androidx.core:core-ktx:${Libraries.Versions.ktx}"
        const val ktxFragment = "androidx.fragment:fragment-ktx:${Libraries.Versions.ktx}"
        const val ktxLifecycle =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Libraries.Versions.ktx}"

        fun DependencyHandler.navigation() {
            add(
                "implementation",
                "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
            )
            add("implementation", "androidx.navigation:navigation-ui-ktx:${Versions.navigation}")
        }
    }

    const val kotlinStdLib =
        "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${BuildPlugins.Versions.kotlinVersion}"
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