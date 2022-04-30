buildscript {
    repositories {
        google()
        mavenCentral()
//        maven {uri("https://plugins.gradle.org/m2/") }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
//        classpath("org.gradle.kotlin:gradle-kotlin-dsl-plugins:2.2.0")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}