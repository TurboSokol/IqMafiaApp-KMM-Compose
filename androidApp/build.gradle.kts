plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("android")
}

android {
    compileSdk = Versions.compile_sdk
    defaultConfig {
        applicationId = "com.turbosokol.iqmafiaapp.android"
        minSdk = Versions.min_sdk
        targetSdk = Versions.compile_sdk
        versionCode = 1
        versionName = "1.0"
        vectorDrawables {
            useSupportLibrary = true
        }
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

repositories {
    jcenter()
    maven { url = uri( "https://jitpack.io") }
    maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots/") }
}

dependencies {
    implementation(project(":shared"))

    //Core
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1")

    //UI
    implementation("androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}")
    implementation("androidx.constraintlayout:constraintlayout-compose:${Versions.constraintLayoutCompose}")
    implementation("androidx.compose.material:material:${Versions.compose}")
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("androidx.compose.ui:ui:${Versions.compose}")
    implementation("androidx.compose.ui:ui-tooling:${Versions.compose}")
    implementation("androidx.compose.ui:ui-tooling-preview:${Versions.compose}")
    implementation("androidx.compose.ui:ui-util:${Versions.compose}")
    implementation("com.google.android.material:material:1.5.0")

    //Navigation
    implementation("androidx.navigation:navigation-compose:2.4.1")
    implementation("com.google.accompanist:accompanist-navigation-animation:0.21.4-beta")

    //Components
    implementation("com.google.accompanist:accompanist-pager:0.20.0")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.18.0")
    implementation("com.google.accompanist:accompanist-insets:${Versions.accompanist}")
    implementation("com.google.accompanist:accompanist-insets-ui:${Versions.accompanist}")
    implementation("com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}")
    implementation("com.google.accompanist:accompanist-permissions:${Versions.accompanist}")
    implementation("com.google.accompanist:accompanist-swiperefresh:${Versions.accompanist}")
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
    implementation("androidx.core:core-splashscreen:1.0.0-beta01")
    implementation("com.budiyev.android:code-scanner:2.1.0")
    implementation("com.airbnb.android:lottie-compose:${Versions.lottie}")
    implementation("org.burnoutcrew.composereorderable:reorderable:0.7.4")

    //Network
    implementation("io.ktor:ktor-client-android:${Versions.ktor}")
    implementation("org.kodein.db:kodein-db-inmemory:${Versions.kodein}")

    //DI
    implementation("io.insert-koin:koin-core:${Versions.koin}")
    implementation("io.insert-koin:koin-android:${Versions.koin}")
    implementation("io.insert-koin:koin-androidx-compose:${Versions.koin}")


    //Tests
    implementation ("androidx.test:rules:1.4.0")
    implementation ("androidx.test:runner:1.4.0")
    implementation ("androidx.compose.ui:ui-test:${Versions.compose}")
    implementation ("androidx.compose.ui:ui-test-junit4:${Versions.compose}")


    androidTestImplementation("androidx.test:core:${Versions.test}")
    androidTestImplementation("androidx.test:runner:${Versions.test}")
    androidTestImplementation("androidx.test:rules:${Versions.test}")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${Versions.compose}")
    androidTestImplementation("io.mockk:mockk-android:${Versions.mockk}")
}