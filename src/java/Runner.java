import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Runner {

    private String main;
    private final List<File> path = new ArrayList<>();

    public void setMain(String main) {
        this.main = main;
    }

    public void addPath(File file) {
        this.path.add(file);
    }

    public void run() throws IOException {
        List<String> arguments = new ArrayList<>();
        arguments.add("java");

        arguments.add("-cp");
        StringBuilder pathString = new StringBuilder();
        for (File path : path) {
            pathString.append(path.getAbsolutePath());
            pathString.append(':');
        }
        pathString.append('.');
        arguments.add(pathString.toString());

        arguments.add(main);
        Process process = Runtime.getRuntime().exec(arguments.toArray(new String[0]));
        process.getInputStream().transferTo(System.out);
        process.getErrorStream().transferTo(System.err);
    }

}
