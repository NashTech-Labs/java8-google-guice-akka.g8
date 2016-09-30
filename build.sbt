name := "java8-google-guice-akka"

version := "1.0"

scalaVersion := "2.11.8"

val akka_version = "2.4.10"

// Do not append Scala versions to the generated artifacts
crossPaths := false

// This forbids including Scala related libraries into the dependency
autoScalaLibrary := false

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http-experimental" % akka_version,
  "com.google.inject" % "guice" % "4.1.0",
  "io.javaslang" % "javaslang" % "2.0.3",
  "com.typesafe.akka" %% "akka-slf4j" % akka_version,
  "ch.qos.logback" % "logback-classic" % "1.1.7",
  "com.typesafe.akka" %% "akka-http-testkit" % akka_version % "test",
  "junit" % "junit" % "4.12" % "test",
  "com.novocode" % "junit-interface" % "0.11" % "test" exclude("junit", "junit")
)

javacOptions in doc ++= Seq("-encoding", "UTF-8", "-source", "1.8")

javacOptions in compile ++= Seq("-encoding", "UTF-8", "-source", "1.8", "-target", "1.8", "-Xlint")

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v", "-a")

initialize := {
  val _ = initialize.value
  if (sys.props("java.specification.version") != "1.8")
    sys.error("Java 8 is required for this project.")
}

mainClass in (Compile, run) := Some("com.knoldus.main.Launcher")