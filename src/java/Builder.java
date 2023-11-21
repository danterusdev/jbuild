import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class Builder {

    private final SourceFiles sourceFiles = new SourceFiles();
    private final List<Library> libraries = new ArrayList<>();
    private File outputDir = null;

    public void addSourceFiles(SourceFiles sourceFiles) {
        this.sourceFiles.addSourceFiles(sourceFiles);
    }

    public void addLibrary(Library library) {
        this.libraries.add(library);
    }

    public void setOutputDirectory(File outputDir) {
        this.outputDir = outputDir;
    }

    public OutputFiles build() throws IOException {
        if (JBuild.LOG) {
            System.out.println("Building...");
        }

        for (Library library : libraries) {
            try {
                library.download();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        outputDir.mkdirs();

        List<String> arguments = new ArrayList<>();
        arguments.add("javac");

        arguments.add("-d");
        arguments.add(outputDir.getAbsolutePath());

        arguments.add("-cp");
        StringBuilder librariesString = new StringBuilder();
        for (Library library : libraries) {
            librariesString.append(library.getLocalFile().getAbsolutePath());
            librariesString.append(':');
        }
        librariesString.append('.');
        arguments.add(librariesString.toString());

        for (File file : sourceFiles.getFiles()) {
            arguments.add(file.getAbsolutePath());
        }
        Process process = Runtime.getRuntime().exec(arguments.toArray(new String[0]));
        process.getInputStream().transferTo(System.out);
        process.getErrorStream().transferTo(System.err);

        OutputFiles outputFiles = new OutputFiles();
        outputFiles.addOutputDirectory(outputDir);

        return outputFiles;
    }
}
