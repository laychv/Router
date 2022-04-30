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
    implementation("org.ow2.asm:asm:9.1")
    implementation("org.ow2.asm:asm-commons:9.1")
}

sourceSets.getByName("main") {
    java.srcDir("src/main/kotlin")
}

gradlePlugin {
    plugins {
        create("RouterPlugin") {
            id = "com.laychv.router.plugin"
            implementationClass = "com.laychv.router.plugin.RouterPlugin"
        }
    }
}