plugins {
    id("com.android.application")
    id("kotlin-multiplatform")
    id("kotlin-android-extensions")
    id("org.jetbrains.kotlin.kapt")
}

configurations.all {
    resolutionStrategy {
        //        force(group = "androidx.test", name = "monitor", version = "1.2.0")
    }
}

android {
    compileSdkVersion(AndroidSdk.compile)
    defaultConfig {
        applicationId = "com.github.jeremyrempel.gitnotes.android"
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
    implementation("androidx.constraintlayout:constraintlayout:1.1.2")
    implementation("androidx.recyclerview:recyclerview:1.0.0")

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
        implementation(appCompat)

        "kapt"(daggerKapt)
    }

    compileOnly("javax.annotation:jsr250-api:1.0")

    testImplementation("androidx.test.espresso:espresso-core:3.3.0-alpha01")
    testImplementation("junit:junit:4.13-beta-3")
    testImplementation("androidx.test:core:1.2.1-alpha01")
    testImplementation("androidx.test:runner:1.3.0-alpha01")
    testImplementation("androidx.test:rules:1.3.0-alpha01")
    testImplementation("androidx.test.ext:junit:1.1.2-alpha01")
    debugImplementation("androidx.fragment:fragment-testing:1.2.0-alpha01")
    testImplementation("org.robolectric:robolectric:4.3")
}