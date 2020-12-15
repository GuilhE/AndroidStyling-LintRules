package com.github.guilhe.lint

import com.android.tools.lint.checks.infrastructure.TestFiles
import com.android.tools.lint.checks.infrastructure.TestLintTask
import org.junit.Test

@Suppress("UnstableApiUsage")
class DirectColorInXmlTest {

    @Test
    fun usingDirectHexColor() {
        val input = """
            <?xml version="1.0" encoding="utf-8"?>
            <layout xmlns:android="http://schemas.android.com/apk/res/android"
                xmlns:app="http://schemas.android.com/apk/res-auto">
            
                    <com.google.android.material.button.MaterialButton
                        android:layout_width="match_parent"
                        android:layout_height="wrap_content"
                        android:background="#FFFFFF" />
            </layout>
            """.trimIndent()

        val output = """
            res/layout/test_layout.xml:8: Warning: Always favor attribute resources instead of direct colors, this way the code is abstracted from its theme colors [DirectColorInXml]
                        android:background="#FFFFFF" />
                        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            0 errors, 1 warnings
        """.trimIndent()

        TestLintTask.lint()
            .files(TestFiles.xml("res/layout/test_layout.xml", input).indented())
            .issues(DirectColorInXml.ISSUE)
            .run()
            .expect(output)
            .expectWarningCount(1)
    }

    @Test
    fun ignoringTools() {
        val input = """
            <?xml version="1.0" encoding="utf-8"?>
            <androidx.constraintlayout.widget.ConstraintLayout
                xmlns:android="http://schemas.android.com/apk/res/android"
                xmlns:app="http://schemas.android.com/apk/res-auto"
                xmlns:custom="http://schemas.android.com/tools"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                custom:background="@android:color/white"/>
            """
            .trimIndent()

        TestLintTask.lint()
            .files(TestFiles.xml("res/layout/test_layout.xml", input).indented())
            .issues(ColorResourceInXml.ISSUE)
            .run()
            .expectClean()
    }
}