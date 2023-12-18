import org.jetbrains.compose.ComposeExtension

group = "com.greenmiststudios"
version = "1.0-SNAPSHOT"

allprojects {
  repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
  }
}

plugins {
  kotlin("jvm") version libs.versions.kotlin.get() apply false
  kotlin("multiplatform") version libs.versions.kotlin.get() apply false
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.compose) apply false
  alias(libs.plugins.kotlin.android) apply false
  alias(libs.plugins.ksp) apply false
  alias(libs.plugins.sqldelight) apply false
  alias(libs.plugins.spotless) apply false
}

allprojects {
  afterEvaluate {
    extensions.findByType<ComposeExtension>()?.apply {
      kotlinCompilerPlugin.set(libs.versions.compose.kotlin.compiler.get())
    }

    extensions.findByType<com.diffplug.gradle.spotless.SpotlessExtension>()?.apply { // if you are using build.gradle.kts, instead of 'spotless {' use:
      kotlin {
        // by default the target is every '.kt' and '.kts` file in the java sourcesets
        ktlint()   // has its own section below
        // licenseHeader '/* (C)$YEAR */' // or licenseHeaderFile
      }
      kotlinGradle {
        // target '*.gradle.kts' // default target for kotlinGradle
        ktlint() // or ktfmt() or prettier()
      }
    }
  }
}