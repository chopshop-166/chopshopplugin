package com.chopshop166.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.bundling.Jar

import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.from
import org.gradle.kotlin.dsl.maven
import org.gradle.kotlin.dsl.repositories
import org.gradle.kotlin.dsl.withType

class ChopShopPlugin : Plugin<Project> {
    override fun apply(project : Project) : Unit = with(project) {
        // Get the Chop Shop configuration
        val extension = extensions.create<ChopShopExtension>("chopshop")
        extension.project = project
        // Add dependencies
        repositories.maven(url = "https://jitpack.io")
        // Make a fat JAR
        tasks.withType<Jar>().configureEach {
            from(configurations.getByName("runtimeClasspath").map { if (it.isDirectory()) {it} else zipTree(it) })
        }
    }
}