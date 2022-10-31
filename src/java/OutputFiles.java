import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.nio.file.Path;

public class OutputFiles {

    private final List<Pair<String, File>> files = new ArrayList<>();

    public void addOutputFile(String packageName, File file) {
        files.add(new Pair<>(packageName, file));
    }

    public void addOutputDirectory(String directory) {
        addOutputDirectory(new File(directory));
    }

    public void addOutputDirectory(File directory) {
        this.addOutputDirectoryInner(directory, directory);
    }

    private void addOutputDirectoryInner(File parentDirectory, File directory) {
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                this.addOutputDirectoryInner(parentDirectory, file);
            } else if (file.getName().endsWith(".class")) {
                Path parentPath = parentDirectory.toPath();
                Path filePath = file.toPath();

                String relativePath = parentPath.relativize(filePath).toString();

                int slashIndex = relativePath.indexOf("/");
                slashIndex = Math.max(slashIndex, 0);
                relativePath = relativePath.substring(0, slashIndex);

                this.addOutputFile(relativePath, file);
            }
        }
    }

    public List<Pair<String, File>> getFiles() {
        return files;
    }

}
