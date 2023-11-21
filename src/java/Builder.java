import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class Builder {

    private final SourceFiles sourceFiles = new SourceFiles();
    private File outputDir = null;

    public void addSourceFiles(SourceFiles sourceFiles) {
        this.sourceFiles.addSourceFiles(sourceFiles);
    }

    public void addLibrary(Library library) {
        try {
            library.download();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setOutputDirectory(File outputDir) {
        this.outputDir = outputDir;
    }

    public OutputFiles build() throws IOException {
        outputDir.mkdirs();

        List<String> arguments = new ArrayList<>();
        arguments.add("javac");
        arguments.add("-d");
        arguments.add(outputDir.getAbsolutePath());
        for (File file : sourceFiles.getFiles()) {
            arguments.add(file.getAbsolutePath());
        }
        Runtime.getRuntime().exec(arguments.toArray(new String[0]));

        OutputFiles outputFiles = new OutputFiles();
        outputFiles.addOutputDirectory(outputDir);

        return outputFiles;
    }
}
