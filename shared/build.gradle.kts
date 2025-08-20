plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.cocoapods)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.android.library)
    alias(libs.plugins.sqldelight)
}

version = "1.0"

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "21"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        version = libs.versions.gradle.get()
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = libs.versions.iosDeploymentTarget.get()
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
    }


    sourceSets {
        val commonMain by getting {
            dependencies {
                //COROUTINES
                api(libs.kotlinx.coroutines.core)

                //DI
                api(libs.koin.core)

                //NETWORK
                implementation(libs.bundles.ktor.common)
                implementation(libs.ktor.client.websockets)
                implementation(libs.ktor.client.auth)

                //DATABASE
                implementation(libs.sqldelight.runtime)

                //SERIALIZATION SETTINGS
                implementation(libs.multiplatform.settings)
                implementation(libs.kotlinx.datetime)

            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val androidMain by getting {
            dependencies{
                implementation(libs.bundles.ktor.android)
                implementation(libs.ktor.server.content.negotiation)
                implementation(libs.androidx.core)
                implementation(libs.androidx.compose.ui)
                implementation(libs.sqldelight.android.driver)
            }
        }

        val androidUnitTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation(libs.junit)
//                implementation(libs.mockk)
//                implementation(libs.koin.core)
                implementation(libs.multiplatform.settings.test)
            }
        }

        val iosX64Main by getting {
            dependencies {
                implementation(libs.bundles.ktor.ios)
                implementation(libs.sqldelight.native.driver)
            }
        }

        val iosArm64Main by getting {
            dependencies {
                implementation(libs.bundles.ktor.ios)
                implementation(libs.sqldelight.native.driver)
            }
        }

        val iosSimulatorArm64Main by getting {
            dependencies {
                implementation(libs.bundles.ktor.ios)
                implementation(libs.sqldelight.native.driver)
            }
        }

        // iOS source sets are now handled by the default hierarchy template
        // No explicit dependsOn calls needed
    }

    // Allow code comments visibility in Swift
    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        binaries.all {
            freeCompilerArgs += "-Xexport-kdoc"
        }
    }

}

android {
    namespace = "com.turbosokol.iqmafiaapp"
    compileSdk = libs.versions.targetSdk.get().toInt()
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

sqldelight {
    database("SqlDatabase") {
        packageName = "com.turbosokol.iqmafia"
    }
}