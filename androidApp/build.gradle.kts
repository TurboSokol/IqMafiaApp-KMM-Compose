import com.android.build.api.dsl.Packaging

plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("android")
    id("org.jetbrains.compose") version Versions.compose
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":shared-ui"))

    //UI
    implementation("com.google.android.material:material:${Versions.material}")
    implementation("androidx.compose.material3:material3:${Versions.composeMaterial3}")

    implementation("androidx.appcompat:appcompat:${Versions.appCompat}")
    implementation("androidx.activity:activity-compose:${Versions.activityCompose}")
    implementation("androidx.compose.ui:ui-tooling-preview:${Versions.uiTooling}")
    debugImplementation("androidx.compose.ui:ui-tooling:${Versions.uiTooling}")

    implementation ("androidx.legacy:legacy-support-v4:${Versions.legacySupport}")

    //Lifecycle
    implementation ("androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtensions}")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}")

    //ViewModel
    api("org.brightify.hyperdrive:multiplatformx-api:${Versions.hyperdrive}")

    //Concurrency
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}")


    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${Versions.compose}")
}

android {
    namespace = "com.turbosokol.iqmafiaapp"

    compileSdk = Versions.targetSdk
    defaultConfig {

        applicationId = "com.turbosokol.iqmafiaapp.android"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = Versions.versionCode
        versionName = Versions.versionCode.toString()
        vectorDrawables {
            useSupportLibrary = true
        }
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
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
    packaging {
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
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")


    //UI
    implementation("androidx.constraintlayout:constraintlayout-compose:${Versions.constraintLayoutCompose}")
    implementation("androidx.compose.material:material:${Versions.compose}")
    implementation("androidx.activity:activity-compose:${Versions.activityCompose}")
    implementation("androidx.compose.ui:ui:${Versions.compose}")
    implementation("androidx.compose.ui:ui-tooling:${Versions.compose}")
    implementation("androidx.compose.ui:ui-tooling-preview:${Versions.compose}")
    implementation("androidx.compose.ui:ui-util:${Versions.compose}")

    //Components
    implementation("com.google.accompanist:accompanist-pager:0.20.0")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.18.0")
    implementation("com.google.accompanist:accompanist-insets:${Versions.accompanist}")
    implementation("com.google.accompanist:accompanist-insets-ui:${Versions.accompanist}")
    implementation("com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}")
    implementation("com.google.accompanist:accompanist-permissions:${Versions.accompanist}")
    implementation("com.google.accompanist:accompanist-swiperefresh:${Versions.accompanist}")
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
    implementation("com.budiyev.android:code-scanner:2.1.0")
    implementation("com.airbnb.android:lottie-compose:${Versions.lottie}")
    implementation("org.burnoutcrew.composereorderable:reorderable:0.7.4")

    //Network
    implementation("io.ktor:ktor-client-android:${Versions.ktor}")

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