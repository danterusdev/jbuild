import java.util.Arrays;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        Class<?> buildClass = Build.class;
        try {
            Build.class.getMethod("build", String[].class).invoke(null, (Object) args);
        } catch (IllegalAccessException | NoSuchMethodException e) {
            System.out.println("Build method not accessible!");
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        };
    }
}
