plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.ksp) apply false
    id("maven-publish")

}

android {
    namespace = "dev.sunnat629"
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

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("openaiAndroid") {
                from(components["release"])
                groupId = "dev.sunnat629"
                artifactId = "openai-android"
                version = "0.1.2"

                pom {
                    packaging = "aar"
                    name.set("openai-android")
                    description.set("Android client named openai-android for OpenAI's API, optimized for Android development, featuring coroutines support for asynchronous operations.")
                    url.set("https://github.com/sunnat629/openai-android.git")
                    inceptionYear.set("2024")

                    licenses {
                        license {
                            name.set("MIT License")
                            url.set("https://opensource.org/licenses/MIT")
                        }
                    }

                    developers {
                        developer {
                            id.set("sunnat629")
                            name.set("Mohi us Sunnat")
                            email.set("suncha629@gmail.com")
                        }
                    }

                    scm {
                        connection.set("scm:git:github.com/sunnat629/openai-android.git")
                        developerConnection.set("scm:git:ssh://github.com/sunnat629/openai-android.git")
                        url.set("https://github.com/sunnat629/openai-android")
                    }
                }
            }
        }
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
    implementation("io.insert-koin:koin-core")
    implementation("io.insert-koin:koin-core-coroutines")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.12")


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

tasks.register("publishAllOpenaiAndroid") {
    group = "publishing"
    description = "Generates metadata, POM, and publishes all OpenaiAndroid publications locally and to other repositories."

    dependsOn(
        "generateMetadataFileForOpenaiAndroidPublication",
        "generatePomFileForOpenaiAndroidPublication",
        "publishOpenaiAndroidPublicationToMavenLocal",
        "publishToMavenLocal"
    )

    doLast {
        println("All OpenaiAndroid publications have been generated and published.")
    }
}