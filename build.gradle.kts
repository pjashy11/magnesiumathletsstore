@file:Suppress("DEPRECATION")

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    dependencies {
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.gradle)
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.kotlin.serialization)
        classpath(libs.hilt.android.gradle.plugin)
        classpath(libs.apollo.gradle.plugin)
    }

}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

