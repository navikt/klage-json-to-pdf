import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val mockkVersion = "1.13.3"
val logstashVersion = "7.2"
val jsoupVersion = "1.15.3"
val openHtmlToPdfVersion = "1.0.10"
val kotlinxHtmlVersion = "0.8.0"
val springDocVersion = "1.6.14"
val springSleuthVersion = "3.1.5"
val problemSpringWebStartVersion = "0.27.0"

repositories {
    mavenCentral()
    jcenter()
}

plugins {
    val kotlinVersion = "1.7.22"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    id("org.springframework.boot") version "2.7.5"
    idea
}

apply(plugin = "io.spring.dependency-management")

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.7.22")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.cloud:spring-cloud-starter-sleuth:$springSleuthVersion")
    implementation("org.zalando:problem-spring-web-starter:$problemSpringWebStartVersion")

    implementation("org.springdoc:springdoc-openapi-ui:$springDocVersion")

    implementation("org.jsoup:jsoup:$jsoupVersion")
    implementation("com.openhtmltopdf:openhtmltopdf-pdfbox:$openHtmlToPdfVersion")
    implementation("com.openhtmltopdf:openhtmltopdf-slf4j:$openHtmlToPdfVersion")
    implementation("com.openhtmltopdf:openhtmltopdf-svg-support:$openHtmlToPdfVersion")

    implementation("ch.qos.logback:logback-classic")
    implementation("net.logstash.logback:logstash-logback-encoder:$logstashVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:$kotlinxHtmlVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-html:$kotlinxHtmlVersion")

    testImplementation("io.mockk:mockk:$mockkVersion")
    testImplementation("org.springframework:spring-mock:2.0.8")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage")
    }
    testImplementation("org.mockito:mockito-inline:4.9.0")
}

idea {
    module {
        isDownloadJavadoc = true
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    this.archiveFileName.set("app.jar")
}

kotlin.sourceSets["main"].kotlin.srcDirs("src/main/kotlin")
