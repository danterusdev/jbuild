javac -Xlint:deprecation -d javabuild src/java/Main.java src/java/SourceFiles.java src/java/Builder.java src/java/OutputFiles.java src/java/JarPackager.java src/java/Pair.java src/java/MavenRepository.java src/java/Library.java src/java/Runner.java src/java/JBuild.java
cd javabuild
jar cf ../$1 Main.class SourceFiles.class Builder.class OutputFiles.class JarPackager.class Pair.class MavenRepository.class MavenRepository\$MavenLibrary.class Library.class Runner.class JBuild.class
cd ..
