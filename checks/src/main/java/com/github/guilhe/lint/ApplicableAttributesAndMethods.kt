package com.github.guilhe.lint

object ApplicableAttributesAndMethods {

    fun getColoredAttributes() = listOf(
        //==== From Android attrs.xml ====
        //format color
        "colorForeground", "colorForegroundInverse", "colorBackground", "colorBackgroundFloating", "colorBackgroundCacheHint",
        "colorPressedHighlight", "colorPressedHighlight", "colorLongPressedHighlight", "colorFocusedHighlight", "colorActivatedHighlight",
        "colorMultiSelectHighlight", "fastScrollTextColor", "colorPrimary", "colorPrimaryDark", "colorSecondary", "colorAccent", "colorControlNormal",
        "colorControlActivated", "colorControlHighlight", "colorButtonNormal", "colorSwitchThumbNormal", "colorProgressBackgroundNormal",
        "colorEdgeEffect", "statusBarColor", "navigationBarColor", "navigationBarDividerColor", "backgroundTint", "foregroundTint",
        "outlineSpotShadowColor", "outlineAmbientShadowColor", "cacheColorHint", "buttonTint", "checkMarkTint", "tint", "progressTint",
        "progressBackgroundTint", "secondaryProgressTint", "indeterminateTint", "thumbTint", "tickMarkTint", "resOutColor", "clickColor",
        "shadowColor", "drawableTint", "headerTextColor", "calendarTextColor", "dayOfWeekBackground", "yearListSelectorColor", "gestureColor",
        "uncertainGestureColor", "daySelectorColor", "dayHighlightColor", "numbersTextColor", "numbersInnerTextColor", "numbersBackgroundColor",
        "numbersSelectorColor", "amPmTextColor", "amPmBackgroundColor", "startColor", "centerColor", "endColor", "color", "strokeColor",
        "fillColor", "iconTint", "keyTextColor", "trackTint", "titleTextColor", "subtitleTextColor",
        //format reference|color
        "colorError", "textColorPrimary", "textColorSecondary", "textColorTertiary", "textColorPrimaryInverse", "textColorSecondaryInverse",
        "textColorTertiaryInverse", "textColorHintInverse", "textColorPrimaryDisableOnly", "textColorPrimaryInverseDisableOnly",
        "textColorPrimaryNoDisable", "textColorSecondaryNoDisable", "textColorPrimaryInverseNoDisable", "textColorSecondaryInverseNoDisable",
        "textColorPrimaryActivated", "textColorSecondaryActivated", "textColorSearchUrl", "textColorHighlightInverse", "textColorLinkInverse",
        "textColorAlertDialogListItem", "searchWidgetCorpusItemBackground", "textUnderlineColor", "editTextColor", "floatingToolbarForegroundColor",
        "panelBackground", "panelFullBackground", "panelColorForeground", "panelColorBackground", "tooltipForegroundColor", "tooltipBackgroundColor",
        "textColor", "textColorHighlight", "textColorHint", "textColorLink", "fullDark", "topDark", "centerDark", "bottomDark", "fullBright",
        "topBright", "centerBright", "bottomBright", "bottomMedium", "centerMedium", "childDivider", "src", "divider", "overScrollHeader",
        "overScrollFooter", "drawableTop", "drawableBottom", "drawableLeft", "drawableRight", "drawableStart", "drawableEnd", "dropDownSelector",
        "popupBackground", "imeFullscreenBackground", "backgroundStacked", "backgroundSplit", "background",
        //==== From Android Material values.xml ====
        //format color
        "colorOnError", "colorOnPrimary", "colorOnPrimarySurface", "colorOnSecondary", "colorOnSurface", "colorPrimarySurface", "colorPrimaryVariant",
        "colorSecondary", "colorSecondaryVariant", "colorSurface", "elevationOverlayColor", "itemTextColor", "rippleColor", "strokeColor",
        "statusBarForeground", "backgroundColor", "badgeTextColor", "itemRippleColor", "chipSurfaceColor", "chipBackgroundColor",
        "chipStrokeColor", "chipIconTint", "closeIconTint", "contentScrim", "statusBarScrim", "iconTint", "rangeFillColor", "itemFillColor",
        "itemStrokeColor", "cardForegroundColor", "checkedIconTint", "itemIconTint", "itemShapeFillColor", "tabIndicatorColor", "tabTextColor",
        "tabSelectedTextColor", "tabIconTint", "tabRippleColor", "hintTextColor", "helperTextTextColor", "errorTextColor", "startIconTint",
        "endIconTint", "boxStrokeColor", "boxBackgroundColor", "passwordToggleTint",
        //format reference|color
        "scrimBackground", "insetForeground"
    )

    fun getResourcesClassMethods() = listOf("getColor")

    fun getColorClassMethods() = listOf("parseColor")
}