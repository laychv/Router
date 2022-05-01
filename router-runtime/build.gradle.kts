plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    `maven-publish`
}

android {
    compileSdk = 31

    defaultConfig {
        minSdk = 23
        targetSdk = 31
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions { jvmTarget = "1.8" }
}

dependencies {

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("com.google.android.material:material:1.4.0")
}

publishing {
    publications {
        create("publishing", MavenPublication::class.java) {
            groupId = "com.laychv.router"
            artifactId = "router-runtime"
            version = "1.0.0"
        }
    }
    repositories {
        maven {
            name = "internal"
            url = uri(layout.buildDirectory.dir("../repos"))
        }
    }
}