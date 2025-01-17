// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package org.jetbrains.kotlin.gradle.newTests.testFeatures

import org.jetbrains.kotlin.gradle.newTests.TestConfigurationDslScope
import org.jetbrains.kotlin.gradle.newTests.TestFeature
import org.jetbrains.kotlin.gradle.newTests.writeAccess

internal object ContentRootsFilteringTestFeature : TestFeature<ContentRootsFilteringConfiguration> {
    override fun renderConfiguration(configuration: ContentRootsFilteringConfiguration): List<String> {
        val hiddenSourceRoots = listOfNotNull(
            "tests".takeIf { configuration.hideTestSourceRoots },
            "resources".takeIf { configuration.hideResourceRoots },
            "android-specific roots".takeIf { configuration.hideAndroidSpecificRoots },
            "generated".takeIf { configuration.hideGeneratedRoots },
        )

        return if (hiddenSourceRoots.isEmpty())
            emptyList()
        else
            listOf("hiding following roots: ${hiddenSourceRoots.joinToString()}")
    }

    override fun createDefaultConfiguration(): ContentRootsFilteringConfiguration = ContentRootsFilteringConfiguration()
}

internal class ContentRootsFilteringConfiguration {
    var hideTestSourceRoots: Boolean = false
    var hideResourceRoots: Boolean = false

    // always hidden for now
    val hideAndroidSpecificRoots: Boolean = true
    val hideGeneratedRoots: Boolean = true
}

private val TestConfigurationDslScope.config: ContentRootsFilteringConfiguration
    get() = writeAccess.getConfiguration(ContentRootsFilteringTestFeature)

interface ContentRootsFilteringDsl {
    var TestConfigurationDslScope.hideTestSourceRoots: Boolean
        get() = config.hideTestSourceRoots
        set(value) { config.hideTestSourceRoots = value }

    var TestConfigurationDslScope.hideResourceRoots: Boolean
        get() = config.hideResourceRoots
        set(value) { config.hideResourceRoots = value }

    // Add more if necessary, see `PrinterRootType`
}
