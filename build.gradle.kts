plugins {
    id("java")
    id("com.gradleup.shadow") version "9.0.0-beta4"
}

group = "com.alttd"
version = "1.0.0-SNAPSHOT"
description = "Altitude Particles plugin."

apply<JavaLibraryPlugin>()

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks {
    withType<JavaCompile> {
        options.encoding = Charsets.UTF_8.name()
    }

    withType<Javadoc> {
        options.encoding = Charsets.UTF_8.name()
    }

    shadowJar {
        archiveFileName.set("${project.name}-${project.version}.jar")
        minimize()
        configurations = listOf(project.configurations.shadow.get())
    }

    build {
        dependsOn(shadowJar)
    }
}

dependencies {
    compileOnly("com.alttd:Galaxy-API:1.21-R0.1-SNAPSHOT")
    compileOnly("com.github.LeonMangler:PremiumVanishAPI:2.9.0-4")
}