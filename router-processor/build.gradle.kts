plugins {
//    id("java-library")
//    id("org.jetbrains.kotlin.jvm") version "1.6.10"
    kotlin("jvm")
    kotlin("kapt")
    `maven-publish`
}

//java {
//    sourceCompatibility = JavaVersion.VERSION_1_8
//    targetCompatibility = JavaVersion.VERSION_1_8
//}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":router-annotation"))

    val autoService = "1.0-rc7"
    kapt("com.google.auto.service", "auto-service", autoService)
    compileOnly("com.google.auto.service", "auto-service-annotations", autoService)
    implementation("com.google.code.gson:gson:2.8.6")
    implementation("com.squareup:javapoet:1.13.0")
}

publishing {
    publications {
        create("publishing", MavenPublication::class.java) {
            groupId = "com.laychv.router"
            artifactId = "router-processor"
            version = "1.0.0"
            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "external"
            url = uri(layout.buildDirectory.dir("../repos/external"))
        }
        maven {
            name = "internal"
            url = uri(layout.buildDirectory.dir("../repos/internal"))
        }
    }
}