![Publish to Bintray](https://github.com/GuilhE/AndroidStyling-LintRules/workflows/Publish%20to%20Bintray/badge.svg)

# AndroidStyling-LintRules

A set of lint rules to prevent us from the temptation of using HEX values or direct color resources instead of attributes, when creating our
 layouts either by xml or programmatically.  
To refresh your knowledge about theming colors: [Material Guidelines](https://material.io/develop/android/theming/color/). 

## Installation

AndroidStyling-LintRules is distributed through [Maven Central](https://search.maven.org/artifact/com.github.guilhe/styling-lint), [Jcenter](https://bintray.com/gdelgado/android/AndroidStyling-LintRules) and [Jitpack](https://jitpack.io/#GuilhE/AndroidStyling-LintRules).

```groovy
implementation 'com.github.guilhe:styling-lint:${LATEST_VERSION}'
```
[![Maven Central](https://img.shields.io/maven-central/v/com.github.guilhe/styling-lint.svg)](https://search.maven.org/search?q=g:com.github.guilhe%20AND%20styling-lint) [![Download](https://api.bintray.com/packages/gdelgado/android/AndroidStyling-LintRules/images/download.svg)](https://bintray.com/gdelgado/android/AndroidStyling-LintRules/_latestVersion)

## Usage

Well this is easy, your styles and layouts should always use attributes to get the color from the corresponding theme.  
Therefore, in situations like:
```xml
<TextView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:textColor="@color/black"/>
```
You'll get a warning advising you to change it:  
<img src="https://raw.githubusercontent.com/GuilhE/AndroidStyling-LintRules/master/media/img1.png?token=AAUNTE3YJGFDS25GHHFEKBC64D4F2" alt="Warning" width="70%"/>

This rules are not exclusive for layouts:
```java
getColor(R.color.colorPrimary)
```
You'll also get a warning advising you to change it:  
<img src="https://raw.githubusercontent.com/GuilhE/AndroidStyling-LintRules/master/media/img2.png?token=AAUNTE5UIPRKOGWF3LD5YO264D4IA" alt="Warning" width="70%"/>

In code situations, getting a color from a theme is not that trivial and it involves a bit of boilerplate code.  
For that reason, [ResourcesProvider-ktx](https://github.com/GuilhE/ResourcesProvider-ktx) may come in hand. It's a helper class to provide
 resources and it has ["themes support"](https://github.com/GuilhE/ResourcesProvider-ktx#themes):
   
```java
.setBackgroundColor(resourcesProvider.colorRes(R.attr.colorPrimary, R.style.App_Style_A)
```  
  
## Bugs and Feedback

For bugs, questions and discussions please use the [Github Issues](https://github.com/GuilhE/AndroidStyling-LintRules/issues).

## LICENSE

Copyright (c) 2020-present GuilhE

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

<http://www.apache.org/licenses/LICENSE-2.0>

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
