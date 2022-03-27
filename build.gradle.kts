buildscript {
    repositories {
        google()
        mavenCentral()
//        maven { url = uri("https://plugins.gradle.org/m2/") }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.1")
        classpath(kotlin("gradle-plugin", version = "1.6.10"))
//        classpath("org.gradle.kotlin:gradle-kotlin-dsl-plugins:2.2.0")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}