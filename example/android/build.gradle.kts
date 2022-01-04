plugins {
    id("com.android.application")
    kotlin("android")
    id("org.jetbrains.compose") version Versions.compose
}

repositories {
    google()
}

dependencies {

    implementation(project(":example:base"))
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("androidx.appcompat:appcompat:1.4.0")
}

apply(from = rootProject.file("gradle/configure-android.gradle"))
apply(from = rootProject.file("gradle/configure-compose.gradle"))
