plugins {
  alias(libs.plugins.compose)
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
}

group = "com.greenmiststudios"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

android {
  namespace = "com.greenmiststudios.tidy"
  compileSdk = 34
  defaultConfig {
    applicationId = "com.greenmiststudios.tidy"
    minSdk = 24
    targetSdk = 34
    versionCode = 1
    versionName = version.toString()
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_11.toString()
  }
  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
    }
  }
}

dependencies {
  implementation(project(":common"))
  implementation(libs.androidx.activity.compose)
}
