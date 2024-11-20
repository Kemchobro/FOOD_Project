plugins {
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jitpack.io") // Ensure JitPack is added here
}

dependencies {
    // JUnit 5 for testing
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // News API dependency with the correct version
    implementation("com.github.KwabenBerko:News-API-Java:1.0.2")
}


tasks.test {
    useJUnitPlatform()
}
