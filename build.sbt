name := "LibLinearSVM"

version := "1.0"

scalaVersion := "2.11.6" //System.getenv.get("SCALA_VERSION")

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.5.0"  

libraryDependencies += "org.apache.spark" %% "spark-mllib" % "1.5.0"

libraryDependencies += "org.jblas" % "jblas" % "1.2.3"

//libraryDependencies ++= Seq("org.apache.spark" %% "spark-core" % "1.4.0", "org.apache.spark" %% "spark-graphx" % "1.4.0")
