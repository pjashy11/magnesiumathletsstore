import java.io.FileInputStream
import java.util.Properties


plugins {
    id("com.android.application")
    id("com.apollographql.apollo")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
    id("kotlin-kapt")
}

android {
    namespace = "com.example.magnesiumathletesstore"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.magnesiumathletesstore"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val shopPropsFile = file("$rootDir/app/shop.properties")
        val shopProps = Properties()

        if (shopPropsFile.exists()) {
            shopProps.load(FileInputStream(shopPropsFile))
        }
        buildConfigField("String", "SHOP_DOMAIN", "\"${shopProps["SHOP_DOMAIN"]}\"")
        buildConfigField("String", "API_KEY", "\"${shopProps["API_KEY"]}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_17)
        targetCompatibility(JavaVersion.VERSION_17)
    }


    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
        viewBinding = true
    }


    packaging {
        resources {
            excludes += "/META-INF/gradle/{AL2.0,LGPL2.1}"
        }
    }



}

dependencies {
    implementation(libs.coil.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.kotlin.stdlib)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.runtime.livedata)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.apollo.runtime)
    implementation(libs.apollo.normalized.cache.sqlite)
    implementation(libs.apollo.normalized.cache)
    implementation(libs.apollo.http.cache)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    debugImplementation(libs.androidx.ui.tooling)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
}

apollo {
    service("service") {
        packageName.set("com.example.magnesiumathletesstore")
        schemaFile.set(file("src/main/graphql/schema.graphqls"))
    }
}

kapt {
    correctErrorTypes = true
    arguments {
        arg("dagger.hilt.android.internal.disableAndroidSuperclassValidation", "true")
        arg("dagger.hilt.android.internal.projectType", "APP")
        arg("dagger.hilt.internal.useAggregatingRootProcessor", "false")
    }
}