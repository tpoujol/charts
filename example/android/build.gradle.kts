plugins {
    id("com.android.application")
    kotlin("android")
    id("org.jetbrains.compose") version Versions.compose
}

apply(from = rootProject.file("gradle/configure-android.gradle"))
apply(from = rootProject.file("gradle/configure-compose.gradle"))

dependencies {

    implementation(Android.appcompat)
    implementation(Android.activityCompose)


    implementation(project(":lib:pie"))
    implementation(project(":lib:bar"))
    implementation(project(":lib:line"))

    implementation(compose.runtime)
    implementation(compose.preview)
    implementation(compose.foundation)
    implementation(compose.ui)
    implementation(compose.animation)
    implementation(compose.material)

}
