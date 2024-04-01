plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kotlinxSerialization)
    id("com.google.devtools.ksp") version "1.9.23-1.0.19" apply false
}

android {
    namespace = "dev.sunnat629.openai_android"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.auth)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)

    // Import the Koin BOM
    implementation(platform("io.insert-koin:koin-bom:3.6.0-wasm-alpha2"))

    // Koin core features for Android
    implementation("io.insert-koin:koin-android")
    // Koin logger for debug purposes
    implementation("io.insert-koin:koin-logger-slf4j")
    // Koin extension for working with coroutines
    implementation("io.insert-koin:koin-core-coroutines")


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}