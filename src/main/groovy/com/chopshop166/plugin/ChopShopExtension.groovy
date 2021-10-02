package com.chopshop166.plugin

import org.gradle.api.java.archives.internal.DefaultManifest

class ChopShopExtension {
    String version = "2021.1.2"
    Boolean useKotlin = false

    Closure manifest(String robotMainClass) {
        def runCommand = { String... args ->
            return args.execute().text.trim()
        }

        def getGitHash = { -> runCommand "git", "describe", "--always" }
        def getGitBranch = { -> runCommand "git", "rev-parse", "--abbrev-ref", "HEAD" }
        def getGitFilesChanged = { -> runCommand ("git", "diff", "--name-only", "HEAD").replaceAll("\n", ", ") }

        return { DefaultManifest mf -> 
            mf.attributes 'Main-Class': robotMainClass
            mf.attributes 'Git-Hash': getGitHash(), 'Git-Branch': getGitBranch(), 'Git-Files': getGitFilesChanged()
            def buildDate = new Date()
            mf.attributes 'Build-Time': buildDate.format("yyyy-MM-dd HH:mm:ss")
        }
    }

    List<String> deps() {
        def depList = ["com.chopshop166:chopshoplib:core:${version}".toString()]
        if (useKotlin)
        {
            depList << ["com.chopshop166:chopshoplib:kotlinext:${version}".toString()]
        }
        return depList
    }
}