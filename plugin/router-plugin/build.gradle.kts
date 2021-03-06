plugins {
    `kotlin-dsl`
    `maven-publish`
}

version = "1.0.2"
group = "router-plugins"

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
    // 这里只能使用 compileOnly 只参与编译
    compileOnly("com.android.tools.build:gradle:7.1.1")
}

sourceSets.getByName("main") {
    java.srcDir("src/main/kotlin")
}

publishing {
    repositories {
        maven {
            name = ""
            url = uri(layout.projectDirectory.dir("../repos"))
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.laychv.router.plugin"
            artifactId = "router-plugin"
            version = "1.0.1"

            from(components["java"])
        }
    }
}

// 如果不配置
// 找不到插件
// :retrofit-plugin:jar: No valid plugin descriptors were found in META-INF/gradle-plugins
gradlePlugin {
    plugins {
        create("RouterPlugin") {
            // 这里替代了 /META-INF/gradle-plugins 中的配置流程（即无需手动配置该目录了）
            id = "com.laychv.router.plugin"
            // 其他工程可以通过id指定的名字来 使用插件
            implementationClass = "com.laychv.router.plugin.RouterPlugin" // 指向对应的 插件实现类
        }
    }
}