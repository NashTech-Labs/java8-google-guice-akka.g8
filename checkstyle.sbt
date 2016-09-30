//---------------------------------------------------
// Setup Checkstyle for code.
// Invocation: sbt checkstyle
// See: https://github.com/etsy/sbt-checkstyle-plugin
//---------------------------------------------------

checkstyleConfigLocation := CheckstyleConfigLocation.Classpath("checkstyle/knoldus_checks.xml")

checkstyleOutputFile :=  target.value / "checkstyle-report.xml"

checkstyleXsltTransformations := {
  Some(Set(CheckstyleXSLTSettings(baseDirectory(_ / "src/main/resources/checkstyle/checkstyle-noframes.xml").value, target(_ / "checkstyle/checkstyle-report.html").value)))
}