package com.github.guilhe.lint

import com.android.tools.lint.detector.api.*
import com.android.tools.lint.detector.api.Category.Companion.CORRECTNESS
import com.intellij.psi.PsiMethod
import org.jetbrains.uast.UCallExpression

@Suppress("UnstableApiUsage")
class ColorWithoutTheme : Detector(), Detector.UastScanner {

    override fun getApplicableMethodNames() = ApplicableAttributesAndMethods.getResourcesClassMethods()

    override fun visitMethodCall(context: JavaContext, node: UCallExpression, method: PsiMethod) {
        when {
            context.evaluator.isMemberInClass(method, "android.content.res.Resources") -> {
                node.valueArguments.getOrNull(0)?.asSourceString()?.let {
                    if (shouldReport(it)) {
                        reportUsage(context, node)
                    }
                }
            }
            context.evaluator.isMemberInClass(method, "android.content.Context") -> {
                node.valueArguments.getOrNull(0)?.asSourceString()?.let {
                    if (shouldReport(it)) {
                        reportUsage(context, node)
                    }
                }
            }
            context.evaluator.isMemberInClass(method, "androidx.core.content.ContextCompat") -> {
                node.valueArguments.getOrNull(1)?.asSourceString()?.let {
                    if (shouldReport(it)) {
                        reportUsage(context, node)
                    }
                }
            }
        }
    }

    private fun shouldReport(value: String): Boolean {
        //if it hits here it's because we have a resource color without theme, no more checks needed
        //let's ignore transparent
        return value != "android.R.color.transparent"
    }

    private fun reportUsage(context: JavaContext, node: UCallExpression) {
        context.report(
            issue = ISSUE,
            scope = node,
            location = context.getLocation(node),
            message = ISSUE.getExplanation(TextFormat.RAW)
        )
    }

    companion object {
        @JvmField
        val ISSUE = Issue
            .create(
                id = "ColorWithoutTheme",
                briefDescription = "Color method without theme reference",
                explanation = """
                    Always favor methods for retrieving colors with an attribute and a theme as parameters. Relying only in color resources won't make the code abstracted from its theme colors.
                    Use ResourcesProvider-ktx (https://github.com/GuilhE/ResourcesProvider-ktx) to make this task easier:
                    resourcesProvider.colorRes(R.attr.colorPrimary, R.style.App_Style_A)
                    resourcesProvider.color(R.attr.colorPrimary, R.style.App_Style_A)
                """.trimIndent(),
                moreInfo = "https://github.com/GuilhE/ResourcesProvider-ktx",
                category = CORRECTNESS,
                priority = 7,
                severity = Severity.WARNING,
                androidSpecific = true,
                implementation = Implementation(ColorWithoutTheme::class.java, Scope.JAVA_FILE_SCOPE)
            )
    }
}