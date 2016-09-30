logLevel := Level.Warn

// Override check style plugin default dependency
libraryDependencies += "com.puppycrawl.tools" % "checkstyle" % "7.1" % "compile"

addSbtPlugin("com.etsy" % "sbt-checkstyle-plugin" % "3.0.0")

addSbtPlugin("de.johoop" % "cpd4sbt" % "1.2.0")

addSbtPlugin("de.johoop" % "jacoco4sbt" % "2.2.0")

addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.8.2")

addSbtPlugin("de.johoop" % "findbugs4sbt" % "1.4.0")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.3")