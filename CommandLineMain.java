import java.util.*;
import ktane_modules.*;
import ktane_modules.Module;

public class CommandLineMain {
    /**
     * Add new modules here
     */
    private static final List<ktane_modules.Module> modules = new LinkedList<>(); static {
        modules.add(new Wires("On the Subject of Wires"));
        modules.add(new Memory("On the Subject of Memory"));
    }
    
    /**
     * Prints the current modules
     */
    private static void printAvailableModules() {
        System.out.println("Available modules: ");
        for(int i = 0; i < modules.size(); i++) {
            System.out.println( String.format("   %d. %s", i+1, modules.get(i).getName()) );
        }
        System.out.println();
    }

    /**
     * Driver code
     */
    public static void main(String[] args) {
        System.out.println(
            "Keep Talking and Nobody Explodes Helper (Version 241) \n" +
            "---------------------------------------------------------\n" +
            "Enter a number from the list to access a module \n " +
            "For this program, you can type:\n" +
            "   > help, for more information about a current module or print out a list of current modules\n" +
            "   > quit, to quit the current module or program" 
            );

        printAvailableModules();

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
                    printAvailableModules();
                    getInput = false;

                /** Run an available module */
                } else {
                    
                    try {
                        System.out.println("\nCurrent Module: " + 
                            modules.get(Integer.parseInt(input) - 1).getName() + 
                            "\n-------------------------------------------"
                        );
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