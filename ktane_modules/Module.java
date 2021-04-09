package ktane_modules;
import java.util.*;
/**
 * Abstract class for modules
 */
public abstract class Module {
    /**
     * Name of the module
     */
    private final String name;
    /**
     * If the program ran as expected, we can choose to break the input loop
     */
    private boolean successfulInput;
    
    /**
     * Module constructor
     * @param name
     */
    protected Module(String name) {
        this.name = name;
        this.successfulInput = false;
    }

    /**
     * Runs the loop until exit
     */
    public void run(Scanner scanner) {
        throw new UnsupportedOperationException("Method has not been implemented yet");
    }

    /**
     * Prints out the help command
     */
    public void help() {
        throw new UnsupportedOperationException("Method has not been implemented yet");
    }

    public String getName() {
        return name;
    }

    public void setSuccessfulInput() {
        this.successfulInput = true;
    }
        
    public boolean isSuccessfulInput() {
        return successfulInput;
    }

    @Override
    public String toString() {
        return String.format("Module{%s}", name);
    }

}
