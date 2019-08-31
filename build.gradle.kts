buildscript {

    repositories {
        maven("https://dl.bintray.com/jetbrains/kotlin-native-dependencies")
        maven("https://dl.bintray.com/kotlin/kotlin-dev/")
        maven("https://plugins.gradle.org/m2/")

        google()
        jcenter()
    }
    dependencies {
        classpath(kotlin("gradle-plugin", version = BuildPlugins.Versions.kotlinVersion))
        classpath(kotlin("serialization", version = BuildPlugins.Versions.kotlinVersion))
        classpath("com.android.tools.build:gradle:${BuildPlugins.Versions.gradleAndroidVersion}")
        classpath("org.jlleitschuh.gradle:ktlint-gradle:${BuildPlugins.Versions.klintVersion}")
    }
}

allprojects {
    repositories {
        maven("https://kotlin.bintray.com/kotlinx")
        maven("https://kotlin.bintray.com/ktor")
        google()
        jcenter()
    }
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint") // Version should be inherited from parent
}