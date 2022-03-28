plugins {
    `embedded-kotlin`
//    kotlin("jvm") version "1.6.10"
//    kotlin("kapt") version "1.6.10"
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(gradleApi())
    implementation(project(":router-annotations"))
    implementation("com.google.auto.service:auto-service:1.0-rc6")
    annotationProcessor("com.google.auto.service:auto-service:1.0-r06")
}
