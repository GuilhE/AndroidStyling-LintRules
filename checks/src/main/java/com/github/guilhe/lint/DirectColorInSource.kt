package com.github.guilhe.lint

import com.android.tools.lint.detector.api.*
import com.android.tools.lint.detector.api.Category.Companion.CORRECTNESS
import com.intellij.psi.PsiMethod
import org.jetbrains.uast.UCallExpression

@Suppress("UnstableApiUsage")
class DirectColorInSource : Detector(), SourceCodeScanner {

    override fun getApplicableMethodNames() = ApplicableAttributesAndMethods.getColorClassMethods()

    override fun visitMethodCall(context: JavaContext, node: UCallExpression, method: PsiMethod) {
        if (context.evaluator.isMemberInClass(method, "android.graphics.Color")) {
            //if it hits here it's because we have a direct color, no more checks needed
            reportUsage(context, node)
        }
    }

    private fun reportUsage(context: JavaContext, node: UCallExpression) {
        context.report(
            issue = ISSUE,
            scope = node,
            location = context.getCallLocation(
                call = node,
                includeReceiver = true,
                includeArguments = true
            ),
            message = ISSUE.getExplanation(TextFormat.RAW)
        )
    }

    companion object {
        @JvmField
        val ISSUE = Issue
            .create(
                id = "DirectColorInSource",
                briefDescription = "Color method without theme reference",
                explanation = """
                    Always favor methods for retrieving colors with an attribute and a theme as parameters. Relying only in color resources won't make the code abstracted from its theme colors.
                    Use ResourcesProvider-ktx (https://github.com/GuilhE/ResourcesProvider-ktx) to make this task easier:
                    resourcesProvider.colorRes(R.attr.colorPrimary, R.style.App_Style_A)
                    resourcesProvider.color(R.attr.colorPrimary, R.style.App_Style_A)
                """.trimIndent(),
                moreInfo = "https://material.io/develop/android/theming/color/",
                category = CORRECTNESS,
                priority = 7,
                severity = Severity.WARNING,
                androidSpecific = true,
                implementation = Implementation(DirectColorInSource::class.java, Scope.JAVA_FILE_SCOPE)
            )
    }
}