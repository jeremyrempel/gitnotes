import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    id("kotlin-multiplatform")
    id("kotlin-android-extensions")
    id("org.jetbrains.kotlin.kapt")
    id("io.gitlab.arturbosch.detekt").version(BuildPlugins.Versions.detekt)
}

configurations.all {
    resolutionStrategy {
        //        force(group = "androidx.test", name = "monitor", version = "1.2.0")
    }
}

android {
    compileSdkVersion(AndroidSdk.compile)
    defaultConfig {
        applicationId = BuildPlugins.androidApplication
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isMinifyEnabled = false
        }
    }

    packagingOptions {
        exclude("META-INF/*.kotlin_module")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    lintOptions {
        isWarningsAsErrors = true
    }
}

kotlin {
    jvm("android")

    targets {
        targetFromPreset(presets.getByName("android"), "androidapp")
    }
}

dependencies {
    implementation(Libraries.kotlinStdLib)
    implementation(project(":lib"))
    implementation("androidx.recyclerview:recyclerview:1.1.0")

    Libraries.Android.apply {

        implementation(coroutines)
        implementation(ktorJson)
        implementation(ktorOkHttp)
        implementation(serialization)
        implementation(dagger)

        // ktx
        implementation(ktxCore)
        implementation(ktxFragment)
        implementation(ktxLifecycle)

        // support
        implementation(lifecycleExt)
        implementation(drawer)
        implementation(appCompat)
        implementation(constraintLayout)
        navigation()
        implementation(material)

        "kapt"(daggerKapt)

        // testing
        debugImplementation(fragmentTesting)
    }

    compileOnly("javax.annotation:jsr250-api:1.0")

    testImplementation("androidx.test.espresso:espresso-core:3.3.0-alpha02")
    testImplementation("junit:junit:4.13.2")
    testImplementation("androidx.test:core:1.4.0-alpha05")
    testImplementation("androidx.test:runner:1.3.0-alpha02")
    testImplementation("androidx.test:rules:1.4.0-alpha04")
    testImplementation("androidx.test.ext:junit:1.1.3-alpha04")
    testImplementation("org.robolectric:robolectric:4.5.1")
}

tasks.withType(KotlinCompile::class.java).all {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}