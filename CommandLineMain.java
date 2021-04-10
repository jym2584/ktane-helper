import java.awt.Desktop;
import java.net.URI;
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
     * A current list of unsupported modules either because they're not yet implemented, or it's currently unfeasible to turn into code
     */
    private static final Map<String, String> unsupportedModules = new LinkedHashMap<>(); static {
        unsupportedModules.put("On the Subject of The Button (to be implemented)", "https://www.bombmanual.com/print/KeepTalkingAndNobodyExplodes-BombDefusalManual-v1.pdf#page=6");
        unsupportedModules.put("On the Subject of Keypads", "https://www.bombmanual.com/print/KeepTalkingAndNobodyExplodes-BombDefusalManual-v1.pdf#page=7");
        unsupportedModules.put("On the Subject of Simon Says", "https://www.bombmanual.com/print/KeepTalkingAndNobodyExplodes-BombDefusalManual-v1.pdf#page=8");
        unsupportedModules.put("On the Subject of Who's on First (to be implemented)", "https://www.bombmanual.com/print/KeepTalkingAndNobodyExplodes-BombDefusalManual-v1.pdf#page=9");
        unsupportedModules.put("On the Subject of Morse Code", "https://www.bombmanual.com/print/KeepTalkingAndNobodyExplodes-BombDefusalManual-v1.pdf#page=12");
        unsupportedModules.put("On the Subject of Complicated Wires (to be implemented)", "https://www.bombmanual.com/print/KeepTalkingAndNobodyExplodes-BombDefusalManual-v1.pdf#page=13");
        unsupportedModules.put("On the Subject of Wire Sequences", "https://www.bombmanual.com/print/KeepTalkingAndNobodyExplodes-BombDefusalManual-v1.pdf#page=14");
        unsupportedModules.put("On the Subject of Mazes (to be implemented)", "https://www.bombmanual.com/print/KeepTalkingAndNobodyExplodes-BombDefusalManual-v1.pdf#page=15"); // might have to use backtracking for this
        unsupportedModules.put("On the Subject of Passwords (to be implemented)", "https://www.bombmanual.com/print/KeepTalkingAndNobodyExplodes-BombDefusalManual-v1.pdf#page=16");
        unsupportedModules.put("On the Subject of Venting Gas", "https://www.bombmanual.com/print/KeepTalkingAndNobodyExplodes-BombDefusalManual-v1.pdf#page=18");
        unsupportedModules.put("On the Subject of Capacitor Discharge", "https://www.bombmanual.com/print/KeepTalkingAndNobodyExplodes-BombDefusalManual-v1.pdf#page=19");
        unsupportedModules.put("On the Subject of Knobs", "https://www.bombmanual.com/print/KeepTalkingAndNobodyExplodes-BombDefusalManual-v1.pdf#page=20");
        unsupportedModules.put("Appendixes", "https://www.bombmanual.com/print/KeepTalkingAndNobodyExplodes-BombDefusalManual-v1.pdf#page=21");
    }

    /**
     * List of module names to use for grabbing its key value from unsupportedModules
     */
    private static final List<String> UNSUPPORTEDMODULES_LIST = new ArrayList<>(unsupportedModules.keySet());

    /**
     * Prints the uncurrent modules
     */
    private static void printAvailableModules() {
        System.out.println("Available modules: ");
        for(int i = 0; i < modules.size(); i++) {
            System.out.println( String.format("   %d. %s", i+1, modules.get(i).getName()));
        }
        System.out.println();
    }

    /**
     * Prints a list of unsupported modules
     */
    private static void printUnsupportedModules() {
        System.out.println("\nUnsupported modules: ");
        System.out.println(" (!) These modules are currently unsupported because they're either not yet implemented or unfeasible to implement. \n" +
        "      You will be redirected to the section's page from the official manual on your default browser.\n");
        for(int i = 0; i < UNSUPPORTEDMODULES_LIST.size(); i++) {
            System.out.println( String.format("   %d. %s", modules.size() + (i + 1), UNSUPPORTEDMODULES_LIST.get(i)));
        }
        System.out.println();
    }

    /**
     * Driver code
     */
    public static void main(String[] args) {
        System.out.println(
            "Keep Talking and Nobody Explodes Helper (Version 241) \n" +
            "---------------------------------------------------------"
        );

        printAvailableModules();
        printUnsupportedModules();

        System.out.println("For this program, you can type:\n" +
        "   > help, for more information about a current module or print out a list of current modules\n" +
        "   > quit, to quit the current module or program\n" + 
        "\nFor the main menu, you can access:\n" +
        "   > help, printsupported, to print out a list of current modules\n" +
        "   > printunsupported, to print out a list of unsupported modules\n" +
        "\n*** Enter a number from the list above to access a module or type 'printsupported' or 'printunsupported' to reprint the list ***\n"
        );

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
                    System.out.println("Goodbye!");
                    break;

                } else if (input.equals("help") || input.equals("printsupported")) {
                    printAvailableModules();
                    getInput = false;
                } else if (input.equals("printunsupported")) {
                    printUnsupportedModules();

                /** Run an available module */
                } else if (Integer.parseInt(input) >= 1 && Integer.parseInt(input) <= modules.size()) { 
                    
                    try {
                        System.out.print(String.format("\033[2J")); // erases previous content
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
                
                /** Opens unsupported module in new browser */
                } else if(Integer.parseInt(input) > modules.size() && Integer.parseInt(input) <= unsupportedModules.size() + modules.size()) {
                    try {
                        Desktop desktop = java.awt.Desktop.getDesktop();
                        System.out.println(String.format("Opening %s (%s) on your default browser...", UNSUPPORTEDMODULES_LIST.get(Integer.parseInt(input) - modules.size() - 1), unsupportedModules.get(UNSUPPORTEDMODULES_LIST.get(Integer.parseInt(input) - modules.size() - 1))));
                        desktop.browse(new URI(unsupportedModules.get(UNSUPPORTEDMODULES_LIST.get(Integer.parseInt(input) - modules.size() - 1))));
                    } catch (Exception e) {
                        // empty
                    }
                }
            } catch (NoSuchElementException nsee) {}
                getInput = true;
        }

    }
}