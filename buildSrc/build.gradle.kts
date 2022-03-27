plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    implementation(gradleApi())
    // 这里可以引用更多其他第三方库
}

gradlePlugin {
    plugins {
        create("RouterPlugin") {
            id = "com.laychv.router"
            implementationClass = "com.laychv.router.RouterPlugin"
        }
    }
}