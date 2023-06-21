plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose") version Versions.compose
}

version = "1.0"

android {
    compileSdk = Versions.targetSdk

    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    version = "1.0"

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }

    buildFeatures {
        compose = true
    }
}

kotlin {
    android()
    ios()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":shared"))

//                api(libs.kermit)
//                api(libs.kermit.crashlytics)
//                api(libs.kotlinx.coroutines.core)
//                api(libs.kotlinx.datetime)
//                api(libs.multiplatformSettings.core)
//                // this enforces new version of atomicfu, the older version from other libraries crashes iOS build
//                api(libs.atomicFu)
//                api(libs.uuid)
//
//                implementation(libs.bundles.ktor.common)
//                implementation(libs.bundles.sqldelight.common)
//
//                implementation(libs.stately.common)

                api(compose.animation)
                api(compose.foundation)
                api(compose.ui)
                api(compose.material)
                api(compose.runtime)
                api(compose.materialIconsExtended)

                api("androidx.compose.compiler:compiler:1.4.7")

                api("io.insert-koin:koin-core:${Versions.koin}")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}")

                //Shared ViewModel
                api("org.brightify.hyperdrive:multiplatformx-api:${Versions.hyperdrive}")
//                api("org.brightify.hyperdrive:multiplatformx-compose: ${Versions.hyperdrive}")

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
//                implementation(libs.accompanist.coil)
            }
        }
        val androidTest by getting {
            dependencies {
//                implementation(libs.test.junit)
//                implementation(libs.test.junitKtx)
//                implementation(libs.test.coroutines)
            }
        }
        val iosMain by getting {
            dependencies {
//                implementation(libs.imageLoader)
            }
        }
        val iosTest by getting {}
    }
}

android {
    namespace = "com.turbosokol.sharedui"
    compileSdk = Versions.targetSdk
    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
    }
}

compose {
    android {
        useAndroidX = true
        androidxVersion = "1.3.0"
    }
}
