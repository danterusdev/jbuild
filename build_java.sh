javac -d build src/java/Main.java src/java/Build.java src/java/SourceFiles.java src/java/Builder.java src/java/OutputFiles.java src/java/JarPackager.java src/java/Pair.java
cd build
jar cf ../testing/JBuild.jar Main.class SourceFiles.class Builder.class OutputFiles.class JarPackager.class Pair.class
cd ..
