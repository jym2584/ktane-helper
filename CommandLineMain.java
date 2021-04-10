import java.util.*;
import ktane_modules.*;
import ktane_modules.Module;

public class CommandLineMain {
    /**
     * Add new modules here
     */
    public static final List<ktane_modules.Module> modules = new LinkedList<>(); static {
        modules.add(new Wires("On the Subject of Wires"));
        modules.add(new Memory("On the Subject of Memory"));
    }
    
    public static void availableModules() {
        for(int i = 0; i < modules.size(); i++) {
            System.out.println( String.format("   %d. %s", i+1, modules.get(i).getName()) );

        }
    }
    public static void main(String[] args) {
        System.out.println("Keep Talking and Nobody Explodes Helper (Version 241) \n" +
        "-----------------------------------------------------");
        System.out.println("Type in the number to access an available module\n");
        System.out.println("Available modules: ");
        availableModules();
        System.out.println();
        boolean getInput = true;
        while(true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print(">> Enter a module number: ");
                String input = "";
                if(getInput) {
                    input = scanner.nextLine().toLowerCase();
                    getInput = false;
                }

                if(input.equals("quit")) {
                    scanner.close();
                    break;

                } else if (input.equals("help")) {
                    availableModules();
                    getInput = false;

                /** Run an available module */
                } else {
                    
                    try {
                        System.out.println(modules.get(Integer.parseInt(input) - 1).getName());
                        modules.get(Integer.parseInt(input) - 1).run(scanner);

                    } catch (IndexOutOfBoundsException iobe) {
                        System.out.println("Make sure to type in the number that's associated with the module.\n");

                    } catch (NumberFormatException nfe) {
                        System.out.println("Make sure to type in the number that's associated with the module.\n");
                    }
                }
            } catch (NoSuchElementException nsee) {}
                getInput = true;
        }

    }
}