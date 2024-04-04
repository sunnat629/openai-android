# Wiki: Interacting with OpenAI Moderations in Android

This wiki guides developers on integrating OpenAI's Moderation API into an Android application, focusing on evaluating content to maintain digital safety and compliance. The example provided demonstrates how to use Kotlin Coroutines within a Jetpack Compose `LaunchedEffect` to asynchronously request content moderation and handle the response.

## Setting Up the Moderation Request

The OpenAI Moderations API evaluates content and determines whether it meets specified safety and compliance standards. Here's a basic setup for making a moderation request:

1. **Prepare Input**: The `.input()` for moderation can be a single string or a list of strings, each representing a piece of content to be evaluated.

2. **Initiate Moderation**: The `.moderate()` function sends the input for evaluation.

3. **Handle the Response**: The API's response is processed to determine the moderation result for each input.

### Code Example

```kotlin
LaunchedEffect(Unit) {
    lifecycleScope.launch {
        openAI.moderations
            .input(listOf(
                "I want to be a footballer.",
                "I want to kill you."
            ))
            .moderate()
            .onStart {
                loading.value = true // Indicate the start of moderation
            }
            .catch { exception ->
                loading.value = false // Handle errors
                chat.value = "Failure: ${exception.message}"
            }
            .collect { response ->
                loading.value = false // Reset loading state
                // Process and display moderation results
                chat.value = response.toString()
                model.value = "flagged: ${response.results?.map { it?.flagged }}"
            }
    }
}
```

## Key Components

- **`input()`**: Sets the content to be moderated. Accepts both single strings and collections of strings.
  
- **`moderate()`**: Initiates the moderation request to the OpenAI API.

- **`onStart { ... }`**: Executed when the moderation request begins, typically used to set a loading indicator.

- **`catch { ... }`**: Catches and handles any exceptions that occur during the request, such as network errors or API limits.

- **`collect { ... }`**: Processes the moderation response. This block will execute with the result of the moderation request, where you can update UI components or state based on the moderation results.

## Handling Responses

The response from the Moderations API typically includes details on whether each piece of content was flagged based on the criteria set within OpenAI's moderation system. This information can be used to filter out inappropriate content, provide feedback to users, or guide content curation processes.

## Conclusion

Integrating OpenAI's Moderation API into Android applications allows developers to leverage powerful AI tools to ensure content integrity and compliance. By following this guide, developers can implement asynchronous content moderation workflows in their apps, enhancing user safety and content quality.