plugins {
    id("java-library")
    id("maven-publish")

    val th2PluginVersion = "0.0.5"
    id("com.exactpro.th2.gradle.grpc") version th2PluginVersion
    id("com.exactpro.th2.gradle.publish") version th2PluginVersion
}

allprojects {
    val version = project.properties["release_version"].toString()
    val suffix = project.properties["version_suffix"]?.toString() ?: ""
    this.group = "com.exactpro.th2"
    this.version = version + if (suffix.isEmpty()) "" else "-$suffix"
}

configurations.all {
    resolutionStrategy.cacheChangingModulesFor(0, "seconds")
    resolutionStrategy.cacheDynamicVersionsFor(0, "seconds")
}

th2Grpc {
    service.set(true)
}

dependencies {
    implementation("com.exactpro.th2:grpc-hand:3.0.0-dev")
}

repositories {
    mavenCentral()
    maven {
        name = "Sonatype_snapshots"
        url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    }
    maven {
        name = "Sonatype_releases"
        url = uri("https://s01.oss.sonatype.org/content/repositories/releases/")
    }
}