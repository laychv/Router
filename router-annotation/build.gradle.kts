plugins {
//    id("java-library")
//    id("org.jetbrains.kotlin.jvm") version "1.6.10"
    kotlin("jvm")
    `maven-publish`
}

//apply(from = "../publish.gradle.kts")

//java {
//    sourceCompatibility = JavaVersion.VERSION_1_8
//    targetCompatibility = JavaVersion.VERSION_1_8
//}

dependencies {
    implementation(kotlin("stdlib"))
}

publishing {
    publications {
        create("publishing", MavenPublication::class.java) {
            groupId = "com.laychv.router"
            artifactId = "router-annotation"
            version = "1.0.0"
            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "external"
            url = uri(rootProject.projectDir.absolutePath + "repos/external")
//                uri(layout.buildDirectory.dir(rootProject.projectDir.absolutePath + "repos/external"))
        }
        maven {
            name = "internal"
            url = uri(rootProject.projectDir.absolutePath + "repos/internal")
//                uri(layout.buildDirectory.dir(rootProject.projectDir.absolutePath + "repos/internal"))
        }
    }
}