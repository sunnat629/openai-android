/**
 * @author  Mohi Us Sunnat
 * @date    01.04.24
 * Copyright ©2024 Sunnat629.dev. All rights reserved.
 */

package dev.sunnat629.openai_client.clients

/**
 * A generic interface for setting up base use case parameters. This interface
 * defines common functionalities that can be configured for different use cases.
 * Implementing classes should specify the type `T` as themselves to allow for fluent method chaining.
 *
 * @param T The type of the implementing class to enable fluent API usage.
 */
interface BaseUseCases<T> {

    /**
     * Sets the model identifier or name.
     *
     * @param model A string representing the model identifier.
     * @return The instance of the implementing class for fluent chaining.
     */
    fun model(model: String): T

    /**
     * Sets the role involved in the use case, such as 'user' or 'system'.
     *
     * @param role A string representing the role.
     * @return The instance of the implementing class for fluent chaining.
     */
    fun role(role: String): T

    /**
     * Sets the text content for the use case.
     *
     * @param text A string representing the text content.
     * @return The instance of the implementing class for fluent chaining.
     */
    fun text(text: String): T

    /**
     * Sets the image URL if the use case involves an image.
     *
     * @param imageUrl A string representing the URL of the image.
     * @return The instance of the implementing class for fluent chaining.
     */
    fun imageUrl(imageUrl: String): T
}
