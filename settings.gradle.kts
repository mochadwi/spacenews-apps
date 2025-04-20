pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://maven.pkg.github.com/auth0/auth0-java")
            credentials {
                username = System.getProperty("gpr.user") ?: System.getenv("GITHUB_USER")
                password = System.getProperty("gpr.key") ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

rootProject.name = "Space News"
include(":app")
