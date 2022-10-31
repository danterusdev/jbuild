javac -d build src/java/Main.java src/java/Build.java src/java/SourceFiles.java src/java/Builder.java
cd build
jar cf ../testing/JBuild.jar Main.class SourceFiles.class Builder.class
cd ..
