name := "archon3"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "org.mongodb.morphia" % "morphia" % "0.105",
  "org.mongodb" % "mongo-java-driver" % "2.11.3"
)

play.Project.playJavaSettings
