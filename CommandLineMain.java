import java.util.*;

public class CommandLineMain {
    public static final Map<String, String> modules = new LinkedHashMap<>(); static {
        modules.put("On the Subject of Wires", "Method goes here");
        modules.put("On the Subject of The Button", "Method goes here");
        modules.put("On the Subject of Simon Says", "Method goes here");
        modules.put("On the Subject of Who's on First", "Method goes here");
    }
    

    public static void main(String[] args) {
        System.out.println(modules);
    }
}