plugins {
  id("com.github.johnrengelman.shadow") version "7.1.2"
  java
}

group = "io.gregory"
version = "1.0"

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.slf4j:slf4j-api:1.7.36")
  implementation("org.slf4j:slf4j-simple:1.7.36")

  testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
  testImplementation("org.mockito:mockito-junit-jupiter:4.5.1")
  testImplementation("org.assertj:assertj-core:3.23.1")

  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
  useJUnitPlatform()
}

tasks.withType<Jar> {
  manifest {
    attributes["Main-Class"] = "io.gregory.Main"
  }
}

tasks {
  build {
    dependsOn(shadowJar)
  }
}