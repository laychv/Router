plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(gradleApi())
    implementation(gradleKotlinDsl())
    implementation(localGroovy())
    implementation(kotlin("stdlib"))
    implementation("com.android.tools.build:gradle:7.1.1")
}

sourceSets.getByName("main") {
    java.srcDir("src/main/kotlin")
}

gradlePlugin {
    plugins {
        create("RouterPlugin") {
            id = "com.laychv.router"
            implementationClass = "com.laychv.router.RouterPlugin"
        }
    }
}