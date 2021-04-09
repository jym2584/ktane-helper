package modules;

public class Wires extends Module {

    protected Wires(String name) {
        super(name);
    }
    
    @Override
    public void run() {
        System.out.println("hi");
    }

    public static void main(String[] args) {
        Module wires = new Wires("On the Subject of Wires");
        wires.run();
    }

    
}
