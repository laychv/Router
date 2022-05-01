plugins {
    `kotlin-dsl`
    `maven-publish`
}

version = "1.0.0"
group = "retrofit-plugins"

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
}

sourceSets.getByName("main") {
    java.srcDir("src/main/kotlin")
}

publishing {
    repositories {
        maven {
            url = uri(layout.projectDirectory.dir("../repos"))
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.laychv.retrofit.plugin"
            artifactId = "retrofit-plugin"
            version = "1.0.0"

            from(components["java"])
        }
    }
}

// 通过id供其他模块使用
gradlePlugin {
    plugins {
        create("RetrofitPlugin") {
            // 这里替代了 /META-INF/gradle-plugins 中的配置流程（即无需手动配置该目录了）
            id = "com.laychv.retrofit.plugin"
            // 其他工程可以通过id指定的名字来 使用插件
            implementationClass = "com.laychv.retrofit.plugin.RetrofitPlugin" // 指向对应的 插件实现类
        }
    }
}