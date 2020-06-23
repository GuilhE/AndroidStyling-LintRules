package com.github.guilhe.lint

import com.android.tools.lint.checks.infrastructure.TestFiles
import com.android.tools.lint.checks.infrastructure.TestLintTask
import org.junit.Test

@Suppress("UnstableApiUsage")
class ColorWithoutThemeTest {

    private val contextStub = """
            package android.content;
            public class Context {
                public final int getColor(int id) {}
            }
            """.trimIndent()

    private val contextCompatStub = """
            package androidx.core.content;
            public class ContextCompat {
                public static int getColor(Context context, int id) {}
            }
            """.trimIndent()

    @Test
    fun callingFromResources() {
        val input = """
            package foo
            import android.content.res.Resources
            class Example {
                fun foo() {
                    val resources: Resources? = null
                    resources.getColor(R.color.black, theme)
                    resources.getColor(android.R.color.black, theme)
                }
            }
            """.trimIndent()

        val output = """
            src/foo/Example.kt:6: Warning: Always favor methods for retrieving colors with an attribute and a theme as parameters. Relying only in color resources won't make the code abstracted from its theme colors.
            Use ResourcesProvider-ktx (https://github.com/GuilhE/ResourcesProvider-ktx) to make this task easier:
            resourcesProvider.colorRes(R.attr.colorPrimary, R.style.App_Style_A)
            resourcesProvider.color(R.attr.colorPrimary, R.style.App_Style_A) [ColorWithoutTheme]
                    resources.getColor(R.color.black, theme)
                    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            src/foo/Example.kt:7: Warning: Always favor methods for retrieving colors with an attribute and a theme as parameters. Relying only in color resources won't make the code abstracted from its theme colors.
            Use ResourcesProvider-ktx (https://github.com/GuilhE/ResourcesProvider-ktx) to make this task easier:
            resourcesProvider.colorRes(R.attr.colorPrimary, R.style.App_Style_A)
            resourcesProvider.color(R.attr.colorPrimary, R.style.App_Style_A) [ColorWithoutTheme]
                    resources.getColor(android.R.color.black, theme)
                    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            0 errors, 2 warnings
        """.trimIndent()

        TestLintTask.lint()
            .files(TestFiles.kotlin(input).indented())
            .issues(ColorWithoutTheme.ISSUE)
            .run()
            .expect(output)
            .expectWarningCount(2)
    }

    @Test
    fun callingFromContext() {
        val input = """
            package foo
            import android.content.Context
            class Example {
                fun foo() {
                    val context: Context? = null
                    context.getColor(R.color.black)
                    context.getColor(android.R.color.black)
                }
            }
            """.trimIndent()

        val output = """
            src/foo/Example.kt:6: Warning: Always favor methods for retrieving colors with an attribute and a theme as parameters. Relying only in color resources won't make the code abstracted from its theme colors.
            Use ResourcesProvider-ktx (https://github.com/GuilhE/ResourcesProvider-ktx) to make this task easier:
            resourcesProvider.colorRes(R.attr.colorPrimary, R.style.App_Style_A)
            resourcesProvider.color(R.attr.colorPrimary, R.style.App_Style_A) [ColorWithoutTheme]
                    context.getColor(R.color.black)
                    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            src/foo/Example.kt:7: Warning: Always favor methods for retrieving colors with an attribute and a theme as parameters. Relying only in color resources won't make the code abstracted from its theme colors.
            Use ResourcesProvider-ktx (https://github.com/GuilhE/ResourcesProvider-ktx) to make this task easier:
            resourcesProvider.colorRes(R.attr.colorPrimary, R.style.App_Style_A)
            resourcesProvider.color(R.attr.colorPrimary, R.style.App_Style_A) [ColorWithoutTheme]
                    context.getColor(android.R.color.black)
                    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            0 errors, 2 warnings
        """.trimIndent()

        TestLintTask.lint()
            .files(
                TestFiles.java(contextStub).indented(),
                TestFiles.kotlin(input).indented()
            )
            .issues(ColorWithoutTheme.ISSUE)
            .run()
            .expect(output)
            .expectWarningCount(2)
    }

    @Test
    fun callingFromContextCompat() {
        val input = """
            package foo
            import androidx.core.content.ContextCompat
            class Example {
                fun foo() {
                    val context: Context? = null
                    ContextCompat.getColor(context, R.color.black)
                    ContextCompat.getColor(context, android.R.color.black)
                }
            }
            """.trimIndent()

        val output = """
            src/foo/Example.kt:6: Warning: Always favor methods for retrieving colors with an attribute and a theme as parameters. Relying only in color resources won't make the code abstracted from its theme colors.
            Use ResourcesProvider-ktx (https://github.com/GuilhE/ResourcesProvider-ktx) to make this task easier:
            resourcesProvider.colorRes(R.attr.colorPrimary, R.style.App_Style_A)
            resourcesProvider.color(R.attr.colorPrimary, R.style.App_Style_A) [ColorWithoutTheme]
                    ContextCompat.getColor(context, R.color.black)
                    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            src/foo/Example.kt:7: Warning: Always favor methods for retrieving colors with an attribute and a theme as parameters. Relying only in color resources won't make the code abstracted from its theme colors.
            Use ResourcesProvider-ktx (https://github.com/GuilhE/ResourcesProvider-ktx) to make this task easier:
            resourcesProvider.colorRes(R.attr.colorPrimary, R.style.App_Style_A)
            resourcesProvider.color(R.attr.colorPrimary, R.style.App_Style_A) [ColorWithoutTheme]
                    ContextCompat.getColor(context, android.R.color.black)
                    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            0 errors, 2 warnings
        """.trimIndent()

        TestLintTask.lint()
            .files(
                TestFiles.java(contextCompatStub).indented(),
                TestFiles.kotlin(input).indented()
            )
            .issues(ColorWithoutTheme.ISSUE)
            .run()
            .expect(output)
            .expectWarningCount(2)
    }

    @Test
    fun ignoringTransparentColor() {
        val input = """
            package foo
            import android.content.res.Resources
            import androidx.core.content.ContextCompat
            class Example {
                fun foo() {
                    val resources: Resources? = null
                    val context: Context? = null
                    resources.getColor(android.R.color.transparent, theme)
                    context.getColor(android.R.color.transparent)
                    ContextCompat.getColor(context, android.R.color.transparent)
                }
            }
            """.trimIndent()

        TestLintTask.lint()
            .files(
                TestFiles.java(contextCompatStub).indented(),
                TestFiles.kotlin(input).indented()
            )
            .issues(ColorWithoutTheme.ISSUE)
            .run()
            .expectClean()
    }
}