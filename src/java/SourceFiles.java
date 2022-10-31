import java.util.List;
import java.util.ArrayList;
import java.io.File;

public class SourceFiles {

    private final List<File> files = new ArrayList<>();

    public void addSourceDirectory(String directory) {
        addSourceDirectory(new File(directory));
    }

    public void addSourceDirectory(File directory) {
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                this.addSourceDirectory(file);
            } else if (file.getName().endsWith(".java")) {
                files.add(file);
            }
        }
    }

    public List<File> getFiles() {
        return files;
    }

}
