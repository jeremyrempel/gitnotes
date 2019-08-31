buildscript {

    repositories {
        maven("https://dl.bintray.com/jetbrains/kotlin-native-dependencies")
        maven("https://dl.bintray.com/kotlin/kotlin-dev/")
        maven("https://plugins.gradle.org/m2/")

        google()
        jcenter()
    }
    dependencies {
        classpath(BuildPlugins.kotlinxSerialization)
        classpath(BuildPlugins.kotlinGradlePlugin)
        classpath(BuildPlugins.androidGradlePlugin)
        classpath(BuildPlugins.kLintPlugin)
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