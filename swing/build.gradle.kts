plugins {
    java
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":core"))
    // https://mvnrepository.com/artifact/cn.hutool/hutool-all
    implementation("cn.hutool:hutool-all:5.8.33")

}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}