package com.github.guilhe.lint

import com.android.tools.lint.checks.infrastructure.TestFiles
import com.android.tools.lint.checks.infrastructure.TestLintTask
import org.junit.Test

@Suppress("UnstableApiUsage")
class ColorResourceInXmlTest {

    @Test
    fun usingDirectResourcesColor() {
        val input = """
            <?xml version="1.0" encoding="utf-8"?>
            <layout xmlns:android="http://schemas.android.com/apk/res/android"
                xmlns:app="http://schemas.android.com/apk/res-auto">
            
                    <com.google.android.material.button.MaterialButton
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:background="@color/white" />
            </layout>
            """.trimIndent()

        val output = """
            res/layout/test_layout.xml:8: Warning: Always favor an attribute instead of a color resource, this way the code is abstracted from its theme colors [ColorResourceInXml]
                        android:background="@color/white" />
                        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            0 errors, 1 warnings
        """.trimIndent()

        TestLintTask.lint()
            .files(TestFiles.xml("res/layout/test_layout.xml", input).indented())
            .issues(ColorResourceInXml.ISSUE)
            .run()
            .expectWarningCount(1)
            .expect(output)
    }

    @Test
    fun usingDirectAndroidResourcesColor() {
        val input = """
            <?xml version="1.0" encoding="utf-8"?>
            <layout xmlns:android="http://schemas.android.com/apk/res/android"
                xmlns:app="http://schemas.android.com/apk/res-auto">
            
                    <com.google.android.material.button.MaterialButton
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:background="@android:color/holo_red_dark" />
            </layout>"""
            .trimIndent()

        val output = """
            res/layout/test_layout.xml:8: Warning: Always favor an attribute instead of a color resource, this way the code is abstracted from its theme colors [ColorResourceInXml]
                        android:background="@android:color/holo_red_dark" />
                        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            0 errors, 1 warnings
        """.trimIndent()

        TestLintTask.lint()
            .files(TestFiles.xml("res/layout/test_layout.xml", input).indented())
            .issues(ColorResourceInXml.ISSUE)
            .run()
            .expectWarningCount(1)
            .expect(output)
    }

    @Test
    fun ignoringTransparent() {
        val input = """
            <?xml version="1.0" encoding="utf-8"?>
            <layout xmlns:android="http://schemas.android.com/apk/res/android"
                xmlns:app="http://schemas.android.com/apk/res-auto">
            
                    <com.google.android.material.button.MaterialButton
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:background="@android:color/transparent" />
            </layout>"""
            .trimIndent()

        TestLintTask.lint()
            .files(TestFiles.xml("res/layout/test_layout.xml", input).indented())
            .issues(ColorResourceInXml.ISSUE)
            .run()
            .expectClean()
    }

    @Test
    fun cleanTestWithAttribute() {
        val input = """
            <?xml version="1.0" encoding="utf-8"?>
            <layout xmlns:android="http://schemas.android.com/apk/res/android"
                xmlns:app="http://schemas.android.com/apk/res-auto">
            
                    <com.google.android.material.button.MaterialButton
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:background="?attr/colorPrimary" />
            </layout>"""
            .trimIndent()

        TestLintTask.lint()
            .files(TestFiles.xml("res/layout/test_layout.xml", input).indented())
            .issues(ColorResourceInXml.ISSUE)
            .run()
            .expectClean()
    }
}