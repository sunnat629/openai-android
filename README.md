
# OpenAI API Client for Android


Android client named `openai-android` for [OpenAI's API](https://platform.openai.com/docs/api-reference), optimized for Android development, featuring coroutines support for asynchronous operations.

## 📦 Setup

### Gradle

For Android projects, Add the OpenAI API Android client to your project by inserting the following dependency in your `build.gradle.kts` file. it's straightforward to set up the client using Gradle:

```kotlin
dependencies {
    implementation("dev.sunnat629.openai_client:openai-android:1.0.0")
}
```

## ⚡️ Getting Started

Initialize `OpenAI` client instance:

```kotlin
val openAI = OpenAIBuilder(token = BuildConfig.OPEN_AI_TOKEN)
    .organization("your_organization_id") // Optional: Set if you have an organization ID
    .refreshToken("your_refresh_token") // Optional: Set if using OAuth2 refresh token
    .headers(mapOf("Custom-Header" to "Value")) // Optional: Set custom headers if needed
    .logging(loggingConfig) // Optional: Configure logging
    .retry(retryConfig) // Optional: Configure retry policy
    .build()

```

To modify the line where you use the OpenAI instance to interact with the API, incorporating the secure use of the TOKEN from your build configuration, visit [here](wiki/01_GettingStarted.md).

## 📚 Wiki
Explore detailed wiki on how to effectively use the OpenAI API client for Android:

#### Available

- [Chat](wiki/Chat.md)
- [Models](wiki/Models.md)
- [Moderations](wiki/Moderations.md)

#### Upcoming

- [Audio](wiki/Audio.md)
- [Assistants](wiki/Assistants.md)
- [Embeddings](wiki/Embeddings.md)
- [Files](wiki/Files.md)
- [Fine-tuning](wiki/Fine-tuning.md)
- [Images](wiki/Images.md)
- [Messages](wiki/Messages.md)
- [Runs](wiki/Runs.md)
- [Threads](wiki/Threads.md)

## ℹ️ Sample Apps

Check out `sample` directory for Android example apps. Instructions on how to run them are in the [README](sample/README.md).

## ⭐️ Support

Like what you see? Here’s how to support the project:

1. **Star it**: Click the star at the top. It helps a lot!
2. **Contribute**: Issues, feature ideas? Your PRs are welcome.
3. **Share your thoughts**: Suggestions? Open an issue or start a discussion.
4. **Buy me a coffee** [PayPal](https://paypal.me/mohi629?country.x=AT&locale.x=en_US)


## 📄 License

This openai-android for OpenAI's API is open-source under the [MIT license](LICENSE).
**Note: This is an unofficial library and is not endorsed by OpenAI.** 
