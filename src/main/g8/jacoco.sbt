import de.johoop.jacoco4sbt.{ScalaHTMLReport, XMLReport}

//----------------------------------------
// Setup Jacoco test coverage.
// Invocation: sbt jacoco:cover
// See: https://github.com/sbt/jacoco4sbt
//----------------------------------------

jacoco.settings

parallelExecution in jacoco.Config := false

jacoco.outputDirectory in jacoco.Config := file("target/jacoco")

jacoco.reportFormats   in jacoco.Config := Seq(XMLReport(encoding = "utf-8"), ScalaHTMLReport(withBranchCoverage = true))

jacoco.excludes        in jacoco.Config := Seq("com.knoldus.main.*")