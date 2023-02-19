import java.util.*

plugins {
    `java-library`
    `maven-publish`
    signing
    id("io.papermc.paperweight.userdev") version "1.3.6"
    id("xyz.jpenilla.run-paper") version "1.0.6" // Adds runServer and runMojangMappedServer tasks for testing
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "me.wawwior"
version = "1.3.8"

val properties = Properties().apply {
    load(rootProject.file("credentials.properties").reader())
}

val mavenUser = properties["mavenUser"]
val mavenPassword = properties["mavenPassword"]

java {
    withJavadocJar()
    withSourcesJar()
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-releases/")
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://libraries.minecraft.net")
    maven("https://repo.wawwior.me/repository/maven-public/")
}

dependencies {

    implementation("org.junit.jupiter:junit-jupiter:5.8.2")
    paperDevBundle("1.19.3-R0.1-SNAPSHOT")

    implementation("me.lucko:commodore:2.0")
    implementation("me.wawwior:config:1.3.8")
}

publishing {
    publications {
        create<MavenPublication>("wawwior-maven") {
            artifactId = "core"
            from(components["java"])
            pom {
                name.set("Core")
                description.set("Core/Library plugin")
                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                developers {
                    developer {
                        id.set("wawwior")
                        name.set("Wawwior")
                    }
                }
            }
        }
    }
    repositories {
        maven {
            name = "maven"
            url = uri("https://repo.wawwior.me/repository/maven-releases/")
            credentials {
                username = "$mavenUser"
                password = "$mavenPassword"
            }
        }
    }
}

tasks.build {
    dependsOn("reobfJar")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}