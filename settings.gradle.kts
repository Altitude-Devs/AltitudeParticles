rootProject.name = "AltitudeParticles"

dependencyResolutionManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        maven("https://repo.destro.xyz/snapshots") // Galaxy
        maven("https://papermc.io/repo/repository/maven-public/") // Paper
        maven("https://jitpack.io") //PremiumVanish
    }
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
}

pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}
