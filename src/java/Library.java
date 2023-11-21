import java.io.File;
import java.io.IOException;

interface Library {
    void download() throws IOException;
    File getLocalFile();
}
