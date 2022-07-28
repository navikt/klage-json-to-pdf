import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val mockkVersion = "1.12.5"
val logstashVersion = "5.1"
val jsoupVersion = "1.11.3"
val openHtmlToPdfVersion = "1.0.10"
val kotlinxHtmlVersion = "0.7.2"
val springFoxVersion = "3.0.0"

repositories {
    mavenCentral()
    jcenter()
}

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.0"
    id("org.springframework.boot") version "2.5.12"
    id("org.jetbrains.kotlin.plugin.spring") version "1.6.0"
    idea
}

apply(plugin = "io.spring.dependency-management")

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.6.0")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    implementation("io.springfox:springfox-boot-starter:$springFoxVersion")

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
    testImplementation("org.mockito:mockito-inline:2.13.0")
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
