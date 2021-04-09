package modules;
/**
 * Abstract class for modules
 */
public abstract class Module {
    /**
     * Name of the module
     */
    private final String name;
    
    /**
     * Module constructor
     * @param name2
     */
    protected Module(String name) {
        this.name = name;
    }

    /**
     * Runs the loop until exit
     */
    public void run() {
        throw new UnsupportedOperationException("Method has not been implemented yet");
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Module{%s}", name);
    }

}
