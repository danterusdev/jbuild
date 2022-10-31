import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.nio.file.Path;

public class SourceFiles {

    private final List<File> files = new ArrayList<>();

    public void addSourceFile(File file) {
        files.add(file);
    }

    public void addSourceDirectory(String directory) {
        addSourceDirectory(new File(directory));
    }

    public void addSourceDirectory(File directory) {
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                this.addSourceDirectory(file);
            } else if (file.getName().endsWith(".java")) {
                this.addSourceFile(file);
            }
        }
    }

    public List<File> getFiles() {
        return files;
    }

}
