package com.github.guilhe.lint

import com.android.resources.ResourceFolderType
import com.android.tools.lint.detector.api.*
import com.android.tools.lint.detector.api.Category.Companion.CORRECTNESS
import org.w3c.dom.Attr

@Suppress("UnstableApiUsage")
class ColorResourceInXml : ResourceXmlDetector() {

    override fun getApplicableAttributes() = ApplicableAttributesAndMethods.getColoredAttributes()

    override fun appliesTo(folderType: ResourceFolderType): Boolean {
        return folderType == ResourceFolderType.LAYOUT ||
                folderType == ResourceFolderType.DRAWABLE ||
                folderType == ResourceFolderType.COLOR ||
                folderType == ResourceFolderType.VALUES //for styles.xml
    }

    override fun visitAttribute(context: XmlContext, attribute: Attr) {
        val ignore = attribute.namespaceURI == "http://schemas.android.com/tools"
                || attribute.value == "@android:color/transparent"
        if (ignore.not() && (attribute.value.startsWith("@color/") || attribute.value.startsWith("@android:color/"))) {
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
                id = "ColorResourceInXml",
                briefDescription = "Color resource used instead of attribute resource",
                explanation = "Always favor an attribute instead of a color resource, this way the code is abstracted from its theme colors",
                moreInfo = "https://material.io/develop/android/theming/color/",
                category = CORRECTNESS,
                priority = 7,
                severity = Severity.WARNING,
                androidSpecific = true,
                implementation = Implementation(
                    ColorResourceInXml::class.java,
                    Scope.RESOURCE_FILE_SCOPE
                )
            )
    }
}