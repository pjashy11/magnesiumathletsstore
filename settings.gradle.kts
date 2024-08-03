pluginManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        gradlePluginPortal()
    }
    plugins {
        id("com.android.application") version "8.5.1"
        id("org.jetbrains.kotlin.android") version "2.0.0"
        id("dagger.hilt.android.plugin") version "2.47"
        id("org.jetbrains.compose") version "1.5.1"
        id("com.apollographql.apollo") version "4.0.0"
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
    }

rootProject.name = "MagnesiumAthletesStore"
include(":app")
