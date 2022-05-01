plugins {
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
            name = "internal"
            url = uri(layout.projectDirectory.dir("../repos"))
        }
    }
}