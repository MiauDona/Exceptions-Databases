plugins {
    id("java")
}

group = "miau.dona"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

    flatDir {
        dirs("libs")
    }
}

dependencies {
    implementation("com.mysql:mysql-connector-j:9.2.0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}