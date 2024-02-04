import com.android.build.api.dsl.ComposeOptions
import org.jetbrains.compose.ComposeExtension

// Manifest version information!
val versionMajor = 1
val versionMinor = 0
val versionPatch = 0
val versionBuild = providers.gradleProperty("build-number").map { it.toInt() }.getOrElse(1)
val versionName = "$versionMajor.$versionMinor.$versionPatch"

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
  alias(libs.plugins.paparazzi) apply false
  alias(libs.plugins.spotless) apply false
}

allprojects {
  ext.set("version", versionName)
  ext.set("versionBuild", versionBuild)

  extensions.findByType(ComposeExtension::class.java)?.apply {
    kotlinCompilerPlugin = libs.versions.composeCompiler.get()
    kotlinCompilerPluginArgs.add("suppressKotlinVersionCompatibilityCheck=2.0.0-Beta3")
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
