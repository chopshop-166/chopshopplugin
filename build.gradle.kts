plugins {
    // Apply the Java Gradle plugin development plugin to add support for developing Gradle plugins
    `java-gradle-plugin`

    // Apply the plugin to publish plugins
    id("com.gradle.plugin-publish") version "0.16.0"

    // Apply the Kotlin plugin to add support for Kotlin
    `kotlin-dsl`
}

group = "com.chopshop166"
version = "0.8"


repositories {
    mavenCentral()
}

dependencies {
    compileOnly(gradleApi())
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
    }
}

pluginBundle {
    // These settings are set for the whole plugin bundle
    website = "http://www.chopshop166.com/"
    vcsUrl = "https://github.com/chopshop-166/chopshopplugin"
    tags = listOf("git", "frc", "wpilib")
}

gradlePlugin {
    // Define the plugin
    val chopshopplugin by plugins.creating {
        displayName = "Chop Shop Gradle Plugin"
        description = "Add Chop Shop dependencies and VCS information"
        id = "com.chopshop166.plugin"
        implementationClass = "com.chopshop166.plugin.ChopShopPlugin"
        version = version
    }
}
