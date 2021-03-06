plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
//    id("com.laychv.router")
    kotlin("kapt")
}

kapt {
    arguments {
        arg("root_project_dir", rootProject.projectDir.absolutePath)
    }
}

android {
    compileSdk = 31

    defaultConfig {
        minSdk = 23
        targetSdk = 31
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
        debug {
            isMinifyEnabled = false
        }
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

    implementation(project(":router-annotation"))
    kapt(project(":router-processor"))
}