plugins {
    java
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":core"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}