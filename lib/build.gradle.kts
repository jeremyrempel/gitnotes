import org.jetbrains.kotlin.config.KotlinCompilerVersion
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
    id("com.android.library")
    id("org.jetbrains.kotlin.native.cocoapods")
}

// CocoaPods requires the podspec to have a version.
version = "1.0"

android {
    compileSdkVersion(AndroidSdk.compile)
    defaultConfig {
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
    }
    buildTypes {
        named("release") {
            isMinifyEnabled = false
        }
        named("debug") {
            // MPP libraries don"t currently get this resolution automatically
            matchingFallbacks = listOf("release")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

kotlin {
    android()
    targets {
        //select iOS target platform depending on the Xcode environment variables
        val iOSTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
            if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
                ::iosArm64
            else
                ::iosX64

        iOSTarget("ios") {
            binaries {
                //                framework {
//                    baseName = "lib"
//                }
            }
        }
    }
    sourceSets {
        all {
            languageSettings.apply {
                progressiveMode = true
                useExperimentalAnnotation("kotlin.Experimental")
            }
        }

        (targets["ios"] as KotlinNativeTarget).compilations["main"].extraOpts.add("-Xobjc-generics")

        commonMain {
            dependencies {
                api(kotlin("stdlib", KotlinCompilerVersion.VERSION))
                implementation("io.ktor:ktor-client-core:${BuildPlugins.Versions.ktor}")
                implementation("io.ktor:ktor-client-json:${BuildPlugins.Versions.ktor}")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${BuildPlugins.Versions.coroutines}")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:${BuildPlugins.Versions.serialization}")
            }
        }
        commonTest {
            dependencies {
                implementation("io.ktor:ktor-client-mock:${BuildPlugins.Versions.ktor}")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${BuildPlugins.Versions.coroutines}")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${BuildPlugins.Versions.coroutines}")
                implementation("io.mockk:mockk-common:${BuildPlugins.Versions.mockk}")

                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }
        val androidMain by getting {
            dependencies {
                api(kotlin("stdlib", KotlinCompilerVersion.VERSION))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${BuildPlugins.Versions.coroutines}")
                implementation("io.ktor:ktor-client-json-jvm:${BuildPlugins.Versions.ktor}")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:${BuildPlugins.Versions.serialization}")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation("io.ktor:ktor-client-mock-jvm:${BuildPlugins.Versions.ktor}")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:${BuildPlugins.Versions.serialization}")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${BuildPlugins.Versions.coroutines}")
                implementation("io.mockk:mockk:${BuildPlugins.Versions.mockk}")

                implementation("com.android.support.test:runner:1.0.2")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:${BuildPlugins.Versions.ktor}")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:${BuildPlugins.Versions.serialization}")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:${BuildPlugins.Versions.coroutines}")
            }
        }
        val iosTest by getting {
            dependencies {
                implementation("io.ktor:ktor-client-mock-native:${BuildPlugins.Versions.ktor}")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:${BuildPlugins.Versions.serialization}")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${BuildPlugins.Versions.coroutines}")
            }
        }
    }
}

//    val cocoapods by tasks.creating(CocoapodsExtension::class) {
//        // Configure fields required by CocoaPods.
//        summary = "Some description for a Kotlin/Native module"
//        homepage = "Link to a Kotlin/Native module homepage"
//    }

tasks.create("iosTest") {
    val device = project.findProperty("iosDevice")?.toString() ?: "iPhone 8"
    dependsOn("linkDebugTestIos")
    doLast {
        val testBinaryPath =
            (kotlin.targets["ios"] as KotlinNativeTarget).binaries.getTest("DEBUG").outputFile.absolutePath
        exec {
            commandLine("xcrun", "simctl", "spawn", device, testBinaryPath)
        }
    }
}
//tasks["check"].dependsOn("iosTest")