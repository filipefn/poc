import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose)
    alias(libs.plugins.android.library)
    id("maven-publish")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    val xcf = XCFramework()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "ComposeApp"
            xcf.add(this)
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.material3)
            implementation(compose.foundation)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.voyager.navigator)
            implementation(libs.coil)
            implementation(libs.coil.network.ktor)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.moko.mvvm)
            implementation(libs.koin.core)
            implementation("org.jetbrains.kotlin:kotlin-stdlib-common")

        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            task("testClasses")
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)
            implementation(libs.kotlinx.coroutines.test)
        }

        androidMain.dependencies {
            implementation(compose.uiTooling)
            implementation(libs.androidx.activityCompose)
            implementation(libs.kotlinx.coroutines.android)
            implementation("org.jetbrains.kotlin:kotlin-stdlib")
            implementation("com.facebook.react:react-native:0.64.0")

        }

        iosMain.dependencies {
            implementation("org.jetbrains.kotlin:kotlin-stdlib")
        }

    }
}

android {
    namespace = "org.stone.co"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["kotlin"])
            groupId = "org.stone.co.shared"
            artifactId = "poc"
            version = "1.0.0"
        }
    }
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/ksdrof500/poc")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME_DO_GITHUB")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN_DO_GITHUB")
            }
        }
    }
}

