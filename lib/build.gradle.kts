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
        named("release"){
            isMinifyEnabled = false
        }
        named("debug"){
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
    targets {
        targets {
            targetFromPreset(presets.getByName("android"), "androidapp")
        }

        //select iOS target platform depending on the Xcode environment variables
        val iOSTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
            if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
                ::iosArm64
            else
                ::iosX64

        iOSTarget("ios") {
            binaries {
                framework {
                    baseName = "lib"
                }
            }
        }

        jvm("android")
    }
    sourceSets {
        sourceSets["commonMain"].dependencies {
            api(kotlin("stdlib", KotlinCompilerVersion.VERSION))
            implementation("io.ktor:ktor-client-core:${BuildPlugins.Versions.ktor}")
            implementation("io.ktor:ktor-client-json:${BuildPlugins.Versions.ktor}")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${BuildPlugins.Versions.coroutines}")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:${BuildPlugins.Versions.serialization}")
        }
        sourceSets["commonTest"].dependencies {
            implementation("io.ktor:ktor-client-mock:${BuildPlugins.Versions.ktor}")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${BuildPlugins.Versions.coroutines}")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${BuildPlugins.Versions.coroutines}")
            implementation("io.mockk:mockk-common:${BuildPlugins.Versions.mockk}")

            implementation(kotlin("test"))
            implementation(kotlin("test-junit"))
        }
        sourceSets["androidMain"].dependencies {
            api(kotlin("stdlib", KotlinCompilerVersion.VERSION))
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${BuildPlugins.Versions.coroutines}")
            implementation("io.ktor:ktor-client-json-jvm:${BuildPlugins.Versions.ktor}")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:${BuildPlugins.Versions.serialization}")
        }
        sourceSets["androidTest"].dependencies {
            implementation("io.ktor:ktor-client-mock-jvm:${BuildPlugins.Versions.ktor}")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:${BuildPlugins.Versions.serialization}")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${BuildPlugins.Versions.coroutines}")
            implementation("io.mockk:mockk:${BuildPlugins.Versions.mockk}")

            implementation("com.android.support.test:runner:1.0.2")
        }
        sourceSets["iosMain"].dependencies {
            implementation("io.ktor:ktor-client-ios:${BuildPlugins.Versions.ktor}")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:${BuildPlugins.Versions.serialization}")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:${BuildPlugins.Versions.coroutines}")
        }
        sourceSets["iosTest"].dependencies {
            implementation("io.ktor:ktor-client-mock-native:${BuildPlugins.Versions.ktor}")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:${BuildPlugins.Versions.serialization}")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${BuildPlugins.Versions.coroutines}")
        }
    }

//    val cocoapods by tasks.creating(CocoapodsExtension::class) {
//        // Configure fields required by CocoaPods.
//        summary = "Some description for a Kotlin/Native module"
//        homepage = "Link to a Kotlin/Native module homepage"
//    }

    val iosTest by tasks.creating(Sync::class) {
        val device = project.findProperty("iosDevice")?.toString() ?: "iPhone 8"
        dependsOn("iosTestBinaries")
        group = JavaBasePlugin.VERIFICATION_GROUP
        description = "Runs tests for target 'ios' on an iOS simulator"

        doLast {
            val binary = kotlin.targets["ios"].compilations["DEBUG"].output.allOutputs.singleFile
//            val binary = kotlin.targets.ios.binaries.getTest("DEBUG").outputFile
            exec {
                commandLine("xcrun", "simctl", "spawn", device, binary.absolutePath)
            }
        }
    }

    tasks.named("check") {
        dependsOn("iosTest")
    }
}