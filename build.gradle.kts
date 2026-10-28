plugins {
    id("java")
    id("application")
    id("com.gradleup.shadow") version "9.6.1"
}

group = "nl.bioinf.dsequencyzer"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    // Unittests
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // PicoCLI
    implementation("info.picocli:picocli:4.7.7")

    // Log4j
    implementation(platform("org.apache.logging.log4j:log4j-bom:2.26.1"))
    implementation("org.apache.logging.log4j:log4j-api")
    runtimeOnly("org.apache.logging.log4j:log4j-core")
}

application {
    mainClass.set("nl.bioinf.dsequencyzer.Main")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "nl.bioinf.dsequencyzer.Main"
    }
}

sourceSets {
    main {
        java {
            exclude(
                "**/SimpleFeature.java",
                "**/GTFParser.java",
                "**/Gene.java",
                "**/FeatureType.java",
                "**/Feature.java",
                "**/Transcript.java"
            )
        }
    }
}