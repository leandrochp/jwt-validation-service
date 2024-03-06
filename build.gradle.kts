import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.3"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.22"
	kotlin("plugin.spring") version "1.9.22"
}

group = "com.github.leandrochp.jwtvalidationservice"
version = "1.0.0"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

sourceSets {
	create("componentTest") {
		compileClasspath += sourceSets["main"].output + sourceSets["test"].output + configurations["testRuntimeClasspath"]
		runtimeClasspath += output + compileClasspath
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web:3.2.1")
	implementation("org.springframework.boot:spring-boot-starter-actuator:3.2.2")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.16.1")
	implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.22")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.22")
	implementation("com.auth0:java-jwt:4.4.0")

	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:3.2.2")

	testImplementation("org.springframework.boot:spring-boot-starter-test:3.2.2")
	testImplementation("io.mockk:mockk:1.13.10")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.create("componentTest", Test::class) {
	description = "Runs the component tests"
	group = "verification"

	testClassesDirs = sourceSets["componentTest"].output.classesDirs
	classpath = sourceSets["componentTest"].runtimeClasspath

	useJUnitPlatform()
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.test {
	finalizedBy("componentTest")
}
