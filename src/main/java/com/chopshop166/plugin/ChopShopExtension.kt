package com.chopshop166.plugin

import org.gradle.api.Action;
import org.gradle.api.Project
import org.gradle.api.java.archives.Manifest

import java.io.ByteArrayOutputStream
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

open class ChopShopExtension(val project : Project) {
    var version = "2023.2.2"

    fun setupManifest() : Action<Manifest> {
        fun List<String>.runCommand(currentWorkingDir: File = File("./")): String {
            val byteOut = ByteArrayOutputStream()
            project.exec {
                workingDir = currentWorkingDir
                commandLine = this@runCommand
                standardOutput = byteOut
            }
            return String(byteOut.toByteArray()).trim()
        }

        val getGitHash = listOf("git", "describe", "--always").runCommand()
        val getGitBranch = listOf("git", "rev-parse", "--abbrev-ref", "HEAD").runCommand()
        val getGitFilesChanged = listOf("git", "diff", "--name-only", "HEAD").runCommand().replace("\n", ", ")

        return Action<Manifest> {
            attributes(mapOf(
                "Git-Hash" to getGitHash,
                "Git-Branch" to getGitBranch,
                "Git-Files" to getGitFilesChanged,
                "Build-Time" to LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
            ))
        }
    }

    fun deps(vararg libs : String) = (listOf("core") + libs).map { "com.chopshop166.chopshoplib:${it}:${version}" }
}