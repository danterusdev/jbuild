import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.jar.JarOutputStream;
import java.util.jar.JarEntry;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class JarPackager {

    private final List<Pair<String, File>> files = new ArrayList<>();

    public void addOutputFiles(OutputFiles outputFiles) {
        files.addAll(outputFiles.getFiles());
    }

    public void doPackage(File outputFile) throws IOException {
        if (outputFile.exists()) {
            outputFile.delete();
        }

        JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(outputFile));
        
        for (Pair<String, File> file : files) {
            String packagePath = file.getA();
            if (!packagePath.isEmpty()) {
                packagePath = packagePath + "/";
            }

            JarEntry jarEntry = new JarEntry(packagePath + file.getB().getName());

            jarOutputStream.putNextEntry(jarEntry);
            byte[] bytes = Files.readAllBytes(file.getB().toPath());
            jarOutputStream.write(bytes, 0, bytes.length);
            jarOutputStream.closeEntry();
        }

        jarOutputStream.close();
    }

}
