javac -Xlint:deprecation -d build src/java/Main.java src/java/SourceFiles.java src/java/Builder.java src/java/OutputFiles.java src/java/JarPackager.java src/java/Pair.java src/java/MavenRepository.java src/java/Library.java
cd build
jar cf ../$1 Main.class SourceFiles.class Builder.class OutputFiles.class JarPackager.class Pair.class MavenRepository.class MavenRepository\$MavenLibrary.class Library.class
cd ..
