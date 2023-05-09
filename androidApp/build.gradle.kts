plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android")
    id("org.jetbrains.compose") version Versions.compose
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":shared-ui"))

    implementation("com.google.android.material:material:${Versions.material}")
    implementation("androidx.appcompat:appcompat:${Versions.appCompat}")
    implementation("androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}")

    implementation("androidx.activity:activity-compose:${Versions.activityCompose}")

    implementation ("androidx.legacy:legacy-support-v4:1.0.0")

    //Database
//    implementation ("com.google.firebase:firebase-firestore:${Versions.firebaseFirestore}")
//    implementation ("com.google.firebase:firebase-core:${Versions.firebaseCore}")
//    implementation("org.kodein.db:kodein-db-inmemory:${Versions.kodeinDb}")


    //Lifecycle
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}")

    //ViewModel
    api("org.brightify.hyperdrive:multiplatformx-api:${Versions.hyperdrive}")

    //Concurrency
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}")
    implementation("androidx.compose.ui:ui-tooling-preview:1.3.3")
    debugImplementation("androidx.compose.ui:ui-tooling:1.3.3")


    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${Versions.compose}")
}

android {

    compileSdk = Versions.targetSdk
    defaultConfig {

        applicationId = "com.turbosokol.iqmafiaapp.android"
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
            isMinifyEnabled = false
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