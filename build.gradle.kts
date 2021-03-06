/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Gradle plugin project to get you started.
 * For more details take a look at the Writing Custom Plugins chapter in the Gradle
 * User Manual available at https://docs.gradle.org/6.0.1/userguide/custom_plugins.html
 */

plugins {
    // Apply the Java Gradle plugin development plugin to add support for developing Gradle plugins
    `java-gradle-plugin`

    // Apply the plugin to publish plugins
    id("com.gradle.plugin-publish") version "0.10.1"

    // Apply the Groovy plugin to add support for Groovy
    groovy

    // Apply wpilib
    id("edu.wpi.first.GradleRIO") version "2020.2.2"
}

repositories {
    // Use jcenter for resolving dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
}

dependencies {
    compileOnly(gradleApi())
}


pluginBundle {
    // These settings are set for the whole plugin bundle
    website = "http://www.chopshop166.com/"
    vcsUrl = "https://github.com/chopshop-166/chopshopplugin"
    tags = listOf("tag", "frc", "wpilib")
}

gradlePlugin {
    // Define the plugin
    val chopshopplugin by plugins.creating {
        displayName = "Chop Shop Gradle Plugin"
        description = "Add Chop Shop dependencies and VCS information"
        id = "com.chopshop166.plugin"
        implementationClass = "com.chopshop166.plugin.ChopShopPlugin"
        version = "0.5"
    }
}
