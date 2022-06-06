plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	kotlin("jvm")
	kotlin("plugin.spring")
	kotlin("plugin.jpa")
	id("com.netflix.dgs.codegen")
}

dependencies {
	api(project(":cubetiq-security-jwt"))

	implementation(platform("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:5.0.2"))
	implementation("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter")
	runtimeOnly("com.netflix.graphql.dgs:graphql-dgs-subscriptions-websockets-autoconfigure")

	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<com.netflix.graphql.dgs.codegen.gradle.GenerateJavaTask> {
	packageName = "com.cubetiqs.graphql.demo.dgmodel"
	schemaPaths = mutableListOf("${projectDir}/src/main/resources/schema")
	generateClient = true
}