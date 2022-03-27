plugins {
//    `kotlin-dsl`
//    `java-library`
//    `java-gradle-plugin`
    `embedded-kotlin`
//    `java-library-convention`
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(gradleApi())
}

sourceSets {
    main {
        java.srcDir("src/main/kotlin")
    }
}