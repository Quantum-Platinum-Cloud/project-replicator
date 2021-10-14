package com.android.gradle.replicator.resgen

import com.google.common.truth.Truth
import org.junit.Test
import java.io.File

class RawGenerationUnitTest: AbstractResourceGenerationTest() {
    @Test
    fun testRawGeneration() {
        val generator = RawResourceGenerator(random)

        val expectedChosenImages = mapOf(
                "raw_aaa.txt" to
                        Pair(testFolder.root, getResource("txt", "bootleg_android.txt")),
                "raw_aab.json" to
                        Pair(testFolder.root, getResource("json", "pizza_recipe.json")),
                "raw_aac.png" to
                        Pair(testFolder.root, getResourceImage("png", "hdpi_bootleg_android.png")),
                "raw_aad.jpg" to
                        Pair(testFolder.root, getResourceImage("jpeg", "hdpi_pizza.jpg"))
        )

        generator.generateResource(
                number = 1,
                outputFolder = testFolder.root,
                resourceQualifiers = listOf(),
                resourceExtension = "txt"
        )

        generator.generateResource(
                number = 1,
                outputFolder = testFolder.root,
                resourceQualifiers = listOf(),
                resourceExtension = "json"
        )

        generator.generateResource(
                number = 1,
                outputFolder = testFolder.root,
                resourceQualifiers = listOf(),
                resourceExtension = "png"
        )

        generator.generateResource(
                number = 1,
                outputFolder = testFolder.root,
                resourceQualifiers = listOf(),
                resourceExtension = "jpg"
        )

        Truth.assertThat(testFolder.root.listFiles()!!.asList().map { it.name }).containsExactly(
                "raw_aaa.txt", "raw_aab.json", "raw_aac.png", "raw_aad.jpg")
        expectedChosenImages.forEach {
            Truth.assertThat(File(it.value.first, it.key).readBytes()).isEqualTo(it.value.second.readBytes())
        }
    }
}