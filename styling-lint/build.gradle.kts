plugins {
    id("com.android.library")
}
apply(from = rootProject.file("deploy-bintray.gradle.kts"))

android {
    compileSdkVersion(AndroidConstants.compileSdkVersions)

    defaultConfig {
        minSdkVersion(AndroidConstants.minSdkVersion)
        targetSdkVersion(AndroidConstants.targetSdkVersion)
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    /** Package the given lint checks library into this AAR  */
    lintPublish(project(":checks"))
}