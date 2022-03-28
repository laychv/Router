plugins {
//    `kotlin-dsl`
//    `java-library`
//    `java-gradle-plugin`
    `embedded-kotlin`
//    `java-library-convention`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(gradleApi())
}

sourceSets {
//    main { java.srcDir("src/main/kotlin") }
    // 或者
    getByName("main").java.srcDir("src/main/kotlin")
}