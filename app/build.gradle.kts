plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.queueless"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.queueless"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    /* -------------------- CORE -------------------- */
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    implementation("androidx.activity:activity-compose:1.9.2")

    /* -------------------- JETPACK COMPOSE -------------------- */
    implementation(platform("androidx.compose:compose-bom:2024.09.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    /* -------------------- NAVIGATION (VERY IMPORTANT) -------------------- */
    implementation("androidx.navigation:navigation-compose:2.7.7")

    /* -------------------- VIEWMODEL + STATE -------------------- */
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.4")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")

    /* -------------------- DATASTORE (TOKEN STORAGE) -------------------- */
    implementation("androidx.datastore:datastore-preferences:1.1.1")

    /* -------------------- COROUTINES -------------------- */
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")

    /* -------------------- NETWORK (FOR OTP APIs - FUTURE) -------------------- */
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    /* -------------------- RETROFIT -------------------- */
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    /* -------------------- OKHTTP (OPTIONAL BUT RECOMMENDED) -------------------- */
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
}
