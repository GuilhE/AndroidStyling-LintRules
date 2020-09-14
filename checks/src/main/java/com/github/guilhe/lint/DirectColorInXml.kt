package com.github.guilhe.lint

import com.android.resources.ResourceFolderType
import com.android.tools.lint.detector.api.*
import com.android.tools.lint.detector.api.Category.Companion.CORRECTNESS
import org.w3c.dom.Attr

@Suppress("UnstableApiUsage")
class DirectColorInXml : ResourceXmlDetector() {

    override fun getApplicableAttributes() = ApplicableAttributesAndMethods.getColoredAttributes()

    override fun appliesTo(folderType: ResourceFolderType): Boolean {
        return folderType == ResourceFolderType.LAYOUT || folderType == ResourceFolderType.COLOR
    }

    override fun visitAttribute(context: XmlContext, attribute: Attr) {
        if (attribute.value.startsWith("#")) {
            reportUsage(context, attribute)
        }
    }

    private fun reportUsage(context: XmlContext, attribute: Attr) {
        context.report(
            issue = ISSUE,
            location = context.getLocation(attribute),
            message = ISSUE.getExplanation(TextFormat.RAW)
        )
    }

    companion object {
        @JvmField
        val ISSUE = Issue
            .create(
                id = "DirectColorInXml",
                briefDescription = "Direct color used instead of attribute resource",
                explanation = "Always favor attribute resources instead of direct colors, this way the code is abstracted from its theme colors",
                moreInfo = "https://material.io/develop/android/theming/color/",
                category = CORRECTNESS,
                priority = 7,
                severity = Severity.WARNING,
                androidSpecific = true,
                implementation = Implementation(DirectColorInXml::class.java, Scope.RESOURCE_FILE_SCOPE)
            )
    }
}