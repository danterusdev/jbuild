import java.util.Arrays;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        Class<?> buildClass = Build.class;
        for (String arg : args) {
            try {
                Build.class.getMethod(arg).invoke(null);
            } catch (NoSuchMethodException e) {
                System.out.println("No method available for task '" + arg + "'!");
            } catch (IllegalAccessException | InvocationTargetException e) {
                System.out.println("Task method '" + arg + "' not accessible!");
            };
        }
    }
}
