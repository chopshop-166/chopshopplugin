package com.chopshop166.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.bundling.Jar

import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.from
import org.gradle.kotlin.dsl.maven
import org.gradle.kotlin.dsl.repositories
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType

class ChopShopPlugin : Plugin<Project> {
    override fun apply(project : Project) : Unit = with(project) {
        // Get the Chop Shop configuration
        extensions.create<ChopShopExtension>("chopshop", this)
        // Add dependencies
        repositories.maven(url = "https://jitpack.io")
        // Add the subsystem template task
        tasks.register<SubsystemTask>("createSubsystem")
    }
}