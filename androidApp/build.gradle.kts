plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.compose)
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":shared-ui"))

    //UI - Compose
    implementation(libs.bundles.compose.ui)
    implementation(libs.bundles.compose.material)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.constraintlayout.compose)

    //UI - Android
    implementation(libs.androidx.appcompat)
    implementation(libs.legacy.support)

    //Lifecycle
    implementation(libs.bundles.androidx.lifecycle)
    implementation(libs.androidx.lifecycle.extensions)

    //ViewModel
    api(libs.hyperdrive)

    //Concurrency
    implementation(libs.kotlinx.coroutines.android)

    //Navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.accompanist.navigation.animation)

    //Components
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)
    implementation(libs.bundles.accompanist.ui)
    implementation(libs.lottie.compose)
    
    //External Components
    implementation(libs.mpandroidchart)
    implementation(libs.reorderable)
    implementation(libs.numberpicker)

    //Network
    implementation(libs.ktor.client.android)

    //DI
    implementation(libs.bundles.koin)

    //Core
    implementation(libs.androidx.core)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)

    //Testing
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
}

android {
    namespace = "com.turbosokol.iqmafiaapp"

    compileSdk = libs.versions.targetSdk.get().toInt()
    defaultConfig {

        applicationId = "com.turbosokol.iqmafiaapp.android"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionCode.get()
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
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlinOptions {
        jvmTarget = "21"
    }

    buildFeatures {
        compose = true
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