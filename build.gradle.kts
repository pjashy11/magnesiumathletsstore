buildscript {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") } // Corrected Kotlin DSL syntax
    }
    dependencies {
        classpath(libs.gradle)
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.hilt.android.gradle.plugin)
    }
}


