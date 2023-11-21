import java.io.InputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class MavenRepository {

    private final String url;
    private final File localPath;

    public MavenRepository(String url, File localPath) {
        this.url = url;
        this.localPath = localPath;
    }

    public MavenLibrary resolveLibrary(String group, String name, String version) {
        return new MavenLibrary(group, name, version);
    }

    public class MavenLibrary implements Library {

        private final String group;
        private final String name;
        private final String version;

        private MavenLibrary(String group, String name, String version) {
            this.group = group;
            this.name = name;
            this.version = version;
        }

        public void download() throws IOException {
            try {
                String subPath = group.replace(".", "/") + "/" + name + "/" + version + "/" + name + "-" + version + ".jar";
                URL fullUrl = new URI(url + subPath).toURL();
                File localPathFile = new File(localPath, subPath);

                if (!localPathFile.getParentFile().exists()) {
                    localPathFile.getParentFile().mkdirs();
                }

                if (localPathFile.exists()) {
                    localPathFile.delete();
                }

                if (JBuild.LOG) {
                    System.out.println("Retrieving maven library " + group + "-" + version + "...");
                }

                InputStream stream = fullUrl.openStream();
                Files.copy(stream, localPathFile.toPath());
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }

        public File getLocalFile() {
            String subPath = group.replace(".", "/") + "/" + name + "/" + version + "/" + name + "-" + version + ".jar";
            return new File(localPath, subPath);
        }

    }

}
