package com.chopshop166.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.bundling.Jar

class ChopShopPlugin implements Plugin<Project> {

    ChopShopExtension extension = null

    void apply(Project project) {
        project.configure(project) {
            // Make a fat JAR
            tasks.withType(Jar).configureEach { Jar jar ->
                jar.from { configurations.runtimeClasspath.collect { it.isDirectory() ? it : zipTree(it) } }
            }

            // Get the Chop Shop configuration
            extension = project.extensions.create('chopshop', ChopShopExtension)

            repositories {
                maven { url 'https://jitpack.io' }
            }
        }
    }
}