chopshopplugin
==============

To use:

```groovy
plugins {
    id "com.chopshop166.plugin" version "0.5"
}
dependencies {
    implementation chopshop.deps()
}

// Setting up my Jar File by adding the manifest so WPILib
// knows where to look for our Robot Class.
jar { manifest chopshop.manifest(ROBOT_MAIN_CLASS) }
```