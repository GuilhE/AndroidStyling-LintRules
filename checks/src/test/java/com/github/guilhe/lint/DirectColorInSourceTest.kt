package com.github.guilhe.lint

import com.android.tools.lint.checks.infrastructure.TestFiles
import com.android.tools.lint.checks.infrastructure.TestLintTask
import org.junit.Test

@Suppress("UnstableApiUsage")
class DirectColorInSourceTest {

    @Test
    fun usingDirectColor() {
        val input = """
            package foo
            import android.graphics.Color
            class Example {
                fun foo() {
                    Color.parseColor("#FFFFFF")
                }
            }
            """.trimIndent()

        val output = """
            src/foo/Example.kt:5: Warning: Always favor methods for retrieving colors with an attribute and a theme as parameters. Relying only in color resources won't make the code abstracted from its theme colors.
            Use ResourcesProvider-ktx (https://github.com/GuilhE/ResourcesProvider-ktx) to make this task easier:
            resourcesProvider.colorRes(R.attr.colorPrimary, R.style.App_Style_A)
            resourcesProvider.color(R.attr.colorPrimary, R.style.App_Style_A) [DirectColorInSource]
                    Color.parseColor("#FFFFFF")
                    ~~~~~~~~~~~~~~~~~~~~~~~~~~~
            0 errors, 1 warnings
        """.trimIndent()

        TestLintTask.lint()
            .files(TestFiles.kotlin(input).indented())
            .issues(DirectColorInSource.ISSUE)
            .run()
            .expect(output)
            .expectWarningCount(1)
    }
}