package modules;
import java.util.*;

public class Wires extends Module {

    protected Wires(String name) {
        super(name);
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        while(true) {

            // Gathers user input
            System.out.print(">> ");
            String input = scanner.nextLine().toLowerCase();
            String tokens[] = input.split(" ");

            // Gather the number of wires given the input
            Map<String, Integer> colorMap = new HashMap<>();
            for(String color: tokens) {
                if (!colorMap.containsKey(color)) {
                    colorMap.put(color, 1);
                } else {
                    colorMap.put(color, colorMap.get(color) + 1);
                }
            }

            
            if (tokens[0].equals("help")) {
                help();
            
            } else if (tokens[0].equals("quit")) {
                break;
            
            } else if(tokens.length == 3 ) { // exactly 3 wires
                System.out.println(colorMap);
                
            } else if (tokens.length > 3 && tokens.length < 7) { // More than 3 and less than 6
                System.out.println(colorMap);

            } else {
                System.out.println("Seems like there is an invalid number of wires...");
                help();
            }
        }
    }

    @Override
    public void help() {
        System.out.println("(?) Type the abbreviations of each color from top to bottom\n" + 
                        "    > Eg: red white yellow black blue\n");
    }

    public static void main(String[] args) {
        Module wires = new Wires("On the Subject of Wires");
        wires.run();
    }

    
}
