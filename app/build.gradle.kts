plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.laychv.router")
    kotlin("kapt")
}

router {
    wikiDir.value(rootDir.absolutePath)
}

// 注解处理器参数，注解处理器获取这里传入的工程路径：/Users/laychv/Projects/Router
// 由自定义gradle插件处理kapt
kapt {
    arguments {
        arg("root_project_dir", rootProject.projectDir.absolutePath)
    }
    keepJavacAnnotationProcessors = true
}

// 自定义task略有改动
tasks.create("wiki") {
    println("路径为：！！！" + rootDir.absolutePath)
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.laychv.router"
        minSdk = 23
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions { jvmTarget = "1.8" }
    buildFeatures { viewBinding = true }
}

dependencies {

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation(project(":app-lib"))
    implementation(project(":router-annotation"))
    kapt(project(":router-processor"))
    implementation(project(":router-runtime"))
}