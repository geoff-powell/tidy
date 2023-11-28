
plugins {
  kotlin("multiplatform")
  alias(libs.plugins.compose)
  alias(libs.plugins.sqldelight)
  id("com.android.library")
  kotlin("native.cocoapods")
}

group = "com.greenmiststudios"
version = "1.0-SNAPSHOT"

@OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
kotlin {
  androidTarget {
    compilations.all {
      kotlinOptions.jvmTarget = "11"
    }
  }
  jvm {
    compilations.all {
      kotlinOptions.jvmTarget = "11"
    }
  }
  js(IR) {
    browser()
  }
  iosX64()
  iosArm64()
  iosSimulatorArm64()

  cocoapods {
    summary = "Common module for Tidy"
    homepage = "https://github.com/geoff-powell/tidy"
    version = "1.0"
    ios.deploymentTarget = "17.0"
    podfile = project.file("../iosApp/Podfile")
    framework {
      baseName = "common"
      isStatic = true
    }
  }

  sourceSets {
    val commonMain by getting {
      dependencies {
        api(compose.runtime)
        api(compose.ui)
        api(compose.foundation)
        api(compose.materialIconsExtended)
        api(compose.material)
        api(compose.material3)
        api(libs.voyager.navigator)
        api(libs.voyager.tab.navigator)
        api(libs.voyager.bottom.sheet.navigator)
        api(libs.koin.core)
        api(libs.koin.compose)
        api(libs.uuid)
        api(libs.sqldelight.coroutines.extensions)

        implementation(libs.ktor.core)
        implementation(libs.molecule.runtime)
      }
    }

    val commonTest by getting {
      dependencies {
        implementation(kotlin("test"))
      }
    }

    val androidMain by getting {
      dependencies {
        api(libs.androidx.appcompat)
        api(libs.androidx.core)
        api(libs.kotlinx.coroutines.android)
        api(libs.koin.android)
        api(libs.koin.androidx.compose)

        implementation(libs.ktor.jvm)
        implementation(libs.sqldelight.driver.android)
      }
    }

    nativeMain.dependencies {
      implementation(libs.sqldelight.driver.native)
    }

    val jvmMain by getting {
      dependencies {
        api(compose.preview)
        implementation(libs.ktor.jvm)
        implementation(libs.sqldelight.driver.jvm)
      }
    }

    val jsMain by getting {
      dependencies {
        api(compose.html.core)
        implementation(libs.ktor.js)
        implementation(libs.ktor.jsonjs)
        implementation(libs.sqldelight.driver.web)
        implementation(devNpm("copy-webpack-plugin", "9.1.0"))
      }
    }

    val iosX64Main by getting {
      dependencies {
        implementation(libs.ktor.ios)
      }
    }
    val iosArm64Main by getting {
      dependencies {
        implementation(libs.ktor.ios)
      }
    }
    val iosSimulatorArm64Main by getting {
      dependencies {
        implementation(libs.ktor.ios)
      }
    }
  }

  explicitApi()
}

android {
  compileSdk = 34
  sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
  defaultConfig {
    minSdk = 24
    targetSdk = 34
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
}

sqldelight {
  databases {
    create("Database") {
      packageName.set("com.greenmiststudios.tidy")
    }
  }
}