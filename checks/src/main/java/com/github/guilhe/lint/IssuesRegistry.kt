package com.github.guilhe.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue

@Suppress("UnstableApiUsage")
class IssuesRegistry : IssueRegistry() {

    override val api: Int = CURRENT_API

    override val issues: List<Issue>
        get() = listOf(
            DirectColorInXml.ISSUE,
            DirectColorInSource.ISSUE,
            ColorResourceInXml.ISSUE,
            ColorWithoutTheme.ISSUE
        )
}