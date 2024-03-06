buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
        classpath("org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}")
        classpath("com.squareup.sqldelight:gradle-plugin:${Versions.sqlDelight}")
        classpath("com.android.tools.build:gradle:${Versions.gradle}")
    }
}

plugins {
    id("io.gitlab.arturbosch.detekt") version Versions.detekt
}

detekt {
    toolVersion = Versions.detekt
    config = files("config/detekt/detekt.yml")
    source = files(
        "${rootProject.rootDir}/LociAndroidApp/src/main/java",
        "${rootProject.rootDir}/LociIosApp/LociIosApp",
        "${rootProject.rootDir}/shared/src"
    )
    baseline = file("${rootProject.projectDir}/config/baseline.xml")
}


allprojects {
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://maven.pkg.jetbrains.space/public/p/kotlinx-coroutines/maven")
        }
        maven {
            url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap")
        }
        maven {
            url = uri("https://androidx.dev/storage/compose-compiler/repository/")
        }

        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
        maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")

    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}