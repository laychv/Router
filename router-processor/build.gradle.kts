plugins {
//    id("java-library")
//    id("org.jetbrains.kotlin.jvm")
    kotlin("jvm")
    kotlin("kapt")
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
}
