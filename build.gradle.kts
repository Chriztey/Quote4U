// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        // other repositories...
        mavenCentral()
    }
    dependencies {
        // other plugins...
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.51.1")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id ("com.google.dagger.hilt.android") version "2.51.1" apply false
    //id("com.google.devtools.ksp") version "2.0.10-1.0.24" apply false
//    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
}