package com.github.guilhe.lint

import com.android.tools.lint.detector.api.TextFormat.RAW
import org.junit.Assert.assertEquals
import org.junit.Test

@Suppress("UnstableApiUsage")
class IssueRegistryTest {

    @Test
    fun issuesExplanationsNotEmpty() {
        val output = IssuesRegistry().issues
            .sortedBy { it.id }
            .joinToString(separator = "\n") { "- **${it.id}** - ${it.getBriefDescription(RAW)}" }

        assertEquals("""
            - **ColorResourceInXml** - Color resource used instead of attribute resource
            - **ColorWithoutTheme** - Color method without theme reference
            - **DirectColorInSource** - Color method without theme reference
            - **DirectColorInXml** - Direct color used instead of attribute resource
            """.trimIndent(), output
        )
    }
}