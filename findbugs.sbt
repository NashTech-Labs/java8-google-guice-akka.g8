import de.johoop.findbugs4sbt.FindBugs._

//-----------------------------------------
// Setup findbugs for sbt
// Invocation: sbt findbugs
// See: https://github.com/sbt/findbugs4sbt
//-----------------------------------------

findbugsSettings

findbugsReportType := Some(de.johoop.findbugs4sbt.ReportType.Html)

findbugsReportPath := Some(target.value / "findbugs" / "findbugs.html")