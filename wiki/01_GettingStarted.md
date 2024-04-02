
# Getting Started with OpenAI Client in Kotlin & Android

This guide provides a streamlined approach to securely initialize the OpenAI client in a Kotlin application, highlighting best practices for API token management and illustrating how to integrate it with dependency injection frameworks like Koin and Hilt.

## Secure API Key Handling with Gradle

To ensure the `OPEN_AI_TOKEN` remains secure and isn't inadvertently shared or leaked, follow these steps:

### Enable `buildConfig` Field Generation in Gradle

```kotlin
android {
    buildFeatures {
        buildConfig = true
    }
}
```

### Access `local.properties` in Your Build Script

Ensure your `local.properties` file contains the `OPEN_AI_TOKEN` and read it into your build configuration:

```kotlin
val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localProperties.load(localPropertiesFile.inputStream())
}

val openAiToken = localProperties.getProperty("OPEN_AI_TOKEN") ?: "null"

android {
    buildTypes {
        debug {
            buildConfigField("String", "OPEN_AI_TOKEN", openAiToken)
        }
        release {
            buildConfigField("String", "OPEN_AI_TOKEN", openAiToken)
        }
    }
}
```

### Use the Token in Your Application Code

Access the securely stored token via the generated `BuildConfig` class:

```kotlin
val token = BuildConfig.OPEN_AI_TOKEN
```


## OpenAI Client Initialization

Initialize your OpenAI client with a focus on security and efficient configuration:

```kotlin
val openAI = OpenAIBuilder(token = BuildConfig.OPEN_AI_TOKEN)
    .organization("your_organization_id") // Optional: Set if you have an organization ID
    .refreshToken("your_refresh_token") // Optional: Set if using OAuth2 refresh token
    .headers(mapOf("Custom-Header" to "Value")) // Optional: Set custom headers if needed
    .logging(loggingConfig) // Optional: Configure logging
    .retry(retryConfig) // Optional: Configure retry policy
    .build()
```

## Dependency Injection with Hilt

For projects using Hilt, provide your OpenAI client instance through a module:

```kotlin
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideOpenAIClient(): OpenAI {
        return OpenAIBuilder(token = BuildConfig.OPEN_AI_TOKEN)
            // Configure as needed
            .build()
    }
}

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
    
    @Inject
    lateinit var openAI: OpenAI
    // Use the injected client
}
```

## Dependency Injection with Koin
Create a Koin module that provides an instance of the OpenAI client. This module will include the initialization logic for the OpenAI client, utilizing the BuildConfig.OPEN_AI_TOKEN for secure API token access.

```kotin
val openAiModule = module {
    single {
         OpenAIBuilder(token = BuildConfig.OPEN_AI_TOKEN)
            // Configure as needed
            .build()
	    }
    }
}

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
	        // Other koin modules
            modules(openAiModule)
        }
    }
}

class MyActivity : AppCompatActivity() {

    // Lazy inject OpenAI client
    private val openAI: OpenAI by inject()

    // Use the injected client
}


```

Following this guide ensures your OpenAI client is correctly initialized, with a strong emphasis on security and modern Android development practices.