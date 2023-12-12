plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose") version Versions.compose
}

version = "1.0"

android {
    namespace = "com.turbosokol.iqmafiaapp"

    compileSdk = Versions.targetSdk

    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    version = "1.0"

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }

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
                api(compose.runtime)
                api(compose.materialIconsExtended)
                api(compose.material3)


                implementation("com.airbnb.android:lottie-compose:${Versions.lottie}")

                api("io.insert-koin:koin-core:${Versions.koin}")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}")

                //Shared ViewModel
                api("org.brightify.hyperdrive:multiplatformx-api:${Versions.hyperdrive}")
                api("com.russhwolf:multiplatform-settings:${Versions.multiplatfomSettings}")

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

        sourceSets["iosSimulatorArm64Main"].dependsOn(iosMain)
        sourceSets["iosSimulatorArm64Test"].dependsOn(iosTest)
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
dependencies {
    implementation("androidx.media3:media3-common:1.2.0")
    implementation("com.google.firebase:firebase-crashlytics-buildtools:2.9.9")
}


compose {
    android {
//        useAndroidX = true
//        androidxVersion = "1.3.0"
    }
}
