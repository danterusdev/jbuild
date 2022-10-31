import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class Builder {
    public static void buildSources(SourceFiles sources, String outputDir) throws IOException {
        buildSources(sources, new File(outputDir));
    }

    public static void buildSources(SourceFiles sources, File outputDir) throws IOException {
        outputDir.mkdirs();

        List<String> arguments = new ArrayList<>();
        arguments.add("javac");
        arguments.add("-d");
        arguments.add(outputDir.getAbsolutePath());
        for (File file : sources.getFiles()) {
            arguments.add(file.getAbsolutePath());
        }
        Runtime.getRuntime().exec(arguments.toArray(new String[0]));
    }
}
