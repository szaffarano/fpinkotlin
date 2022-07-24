import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jlleitschuh.gradle.ktlint")

    kotlin("jvm")
    kotlin("kapt")
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform { }
}

val arrowVersion = "0.10.2"
dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.arrow-kt:arrow-core-data:$arrowVersion")
    implementation("io.arrow-kt:arrow-fx:$arrowVersion")
    implementation("io.arrow-kt:arrow-mtl:$arrowVersion")
    implementation("io.arrow-kt:arrow-syntax:$arrowVersion")
    implementation(
        "org.jetbrains.kotlinx:kotlinx-collections-immutable:0.2"
    )
    implementation("io.github.microutils:kotlin-logging:1.7.8")
    implementation("org.awaitility:awaitility:4.2.0")
    runtimeOnly("org.slf4j:slf4j-simple:1.7.36")

    // need this at compile level for chapter 8
    implementation("io.kotlintest:kotlintest-runner-junit5:3.3.2")
    kapt("io.arrow-kt:arrow-meta:$arrowVersion")
}

repositories {
    jcenter()
    mavenCentral()
    maven("https://dl.bintray.com/kotlin/kotlinx")
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.suppressWarnings = true
}

ktlint {
    debug.set(false)
    verbose.set(true)
    disabledRules.set(
        setOf(
            "comment-spacing",
            "filename",
            "import-ordering",
            "no-line-break-before-assignment"
        )
    )
}

kapt {
    useBuildCache = false
}
