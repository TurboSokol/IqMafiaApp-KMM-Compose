plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.compose)
}

version = "1.0"

android {
    namespace = "com.turbosokol.iqmafiaapp"

    compileSdk = libs.versions.targetSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    version = "1.0"

    buildFeatures {
        compose = true
    }

    sourceSets {
        val main by getting
        main.java.setSrcDirs(listOf("src/androidMain/kotlin"))
        main.res.setSrcDirs(listOf("src/androidMain/res"))
        main.resources.setSrcDirs(
            listOf(
                "src/androidMain/resources",
                "src/commonMain/resources",
            )
        )
        main.manifest.srcFile("src/androidMain/AndroidManifest.xml")
    }
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "21"
            }
        }
    }
    ios()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":shared"))

                // Compose Multiplatform
                api(compose.animation)
                api(compose.foundation)
                api(compose.ui)
                api(compose.runtime)
                api(compose.materialIconsExtended)
                api(compose.material3)

                // UI Components
                // Note: lottie-compose moved to androidMain since it's Android-specific

                // Core
                api(libs.koin.core)
                api(libs.kotlinx.coroutines.core)

                //Shared ViewModel
                api(libs.hyperdrive)
                api(libs.multiplatform.settings)

            }
        }
        val commonTest by getting {
            dependencies {
//                implementation(libs.multiplatformSettings.test)
//                implementation(libs.kotlin.test.common)
//                implementation(libs.koin.test)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.compose.material3)
                // UI Components (Android-specific)
                implementation(libs.lottie.compose)
            }
        }
        val androidUnitTest by getting {
            dependencies {

            }
        }
        val iosMain by getting {
            dependencies {
            }
        }
        val iosTest by getting {}

        // iOS source sets dependencies are now handled by the default hierarchy template
    }
}

android {
    namespace = "com.turbosokol.sharedui"
    compileSdk = libs.versions.targetSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}




compose {

}
