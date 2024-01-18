package com.chopshop166.plugin

import org.gradle.api.Action;
import org.gradle.api.Project
import org.gradle.api.java.archives.Manifest

import java.io.ByteArrayOutputStream
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

open class ChopShopExtension(val project : Project) {
    var version = "2024.1.3"

    fun deps(vararg libs : String) = (listOf("core") + libs).map { "com.chopshop166.chopshoplib:${it}:${version}" }
}