buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.3")
        classpath(kotlin("gradle-plugin", version = "1.6.21"))
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}