//-------------------------------------------------------
// Setup sbt dependency graph
// Invocation: sbt dependencyGraph
// See: https://github.com/jrudolph//sbt-dependency-graph
//-------------------------------------------------------

filterScalaLibrary := false // include scala library in output

dependencyDotFile := file("dependencies.dot") //render dot file to `./dependencies.dot`