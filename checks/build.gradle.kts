plugins {
    java
    id("com.android.lint")
}

apply(plugin = "kotlin")

lintOptions {
    htmlReport = true
    htmlOutput = file("$project.buildDir/outputs/lint/styling-lint-report.html")
    textReport = true
    isAbsolutePaths = false
    isIgnoreTestSources = true
}

dependencies {
    compileOnly(Libs.kotlin)
    compileOnly(Libs.lint_api)
    compileOnly(Libs.lint_checks)
    testImplementation(Libs.lint)
    testImplementation(Libs.lint_tests)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}