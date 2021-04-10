package ktane_modules;
import java.util.*;
/**
 * Wires module
 */
public class Wires extends Module {

    public Wires(String name) {
        super(name);
    }

    /**
     * Driver code for gathering color inputs
     */
    @Override
    public void run(Scanner scanner) {
        while(true) {
            if(isSuccessfulInput()) {
                break;
            }
            /* Gathering user input */
            System.out.print(">> ");
            String input = scanner.nextLine().toLowerCase();
            String tokens[] = input.split(" ");

            /* Gather the number of wires based on the input */
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

            /* Conditional if there is exactly 3 wires */
            } else if(tokens.length == 3 ) { 
                if(!colorMap.containsKey("red")) { // If there are no red wires, cut the second wire
                    System.out.println("Cut the second wire.");

                } else if (tokens[2].equals("white")) { // Otherwise, if the last wire is white, cut the last wire.
                    System.out.println("Cut the last wire.");

                } else if (colorMap.containsKey("blue") && colorMap.get("blue") > 1) { // Otherwise, if there is more than one blue wire, cut the last blue wire.
                    System.out.println("Cut the last blue wire.");

                } else { // Otherwise, cut the last wire.
                    System.out.println("Cut the last wire.");
                }

                setSuccessfulInput();
            /* If there are between 3 to 6 wires */
            } else if (tokens.length > 3 && tokens.length < 7) {
                /* Asking if the serial number is odd */
                System.out.print(">> Is the serial number odd? (y/any other input for no): ");
                String askIfOdd = scanner.nextLine().toLowerCase();

                if(askIfOdd.equals("y")) {
                    serialNumberOdd(tokens, colorMap);
                }

                /* Conditional for 4 wires */
                if(tokens.length == 4) {
                    if (tokens[3].equals("yellow") && !colorMap.containsKey("red")) {
                        System.out.println("Cut the first wire.");

                    } else if (colorMap.containsKey("blue") && colorMap.get("blue") == 1) {
                        System.out.println("Cut the first wire.");

                    } else if (colorMap.containsKey("yellow") && colorMap.get("yellow") > 1) {
                        System.out.println("Cut the last wire.");

                    } else {
                        System.out.println("Cut the second wire.");
                    }

                    setSuccessfulInput();
                /* Conditional for 5 wires */
                } else if (tokens.length == 5) {
                    if(colorMap.containsKey("red") && colorMap.get("red") == 1 && colorMap.containsKey("yellow") && colorMap.get("yellow") > 1) {
                            System.out.println("Cut the first wire.");

                    } else if (!colorMap.containsKey("black")) {
                        System.out.println("Cut the second wire.");
                        
                    } else {
                        System.out.println("Cut the first wire.");
                    }

                    setSuccessfulInput();
                /* 6 wires */
                } else {
                    if(colorMap.containsKey("yellow") && colorMap.get("yellow") == 1 && colorMap.containsKey("white") && colorMap.get("white") > 1) {
                        System.out.println("Cut the fourth wire.");

                    } else if (!colorMap.containsKey("red")) {
                        System.out.println("Cut the last wire.");
                        
                    } else {
                        System.out.println("Cut the fourth wire.");
                    }

                    setSuccessfulInput();
                }



            } else {
                System.out.println("Seems like the input is incorrect.");
                help();
            }
        }
    }

    /**
     * Runs the check if the serial number is odd
     * @param tokens collecting the user's input
     * @param colorMap used to check the number of occurrences of each color
     */
    public void serialNumberOdd(String[] tokens, Map<String, Integer> colorMap) {
        if(tokens.length == 4 && colorMap.containsKey("red") && colorMap.get("red") > 1) { // If there is more than one red wire and the last digit of the serial number is odd
            System.out.println("Cut the last red wire.");

        } else if (tokens.length == 5 && tokens[4].equals("black")) { // If the last wire is black and the last digit of the serial number is odd
            System.out.println("Cut the fourth wire.");

        } else if (tokens.length == 6 && !colorMap.containsKey("yellow")) { // If there are no yellow wires and the last digit of the serial number is odd
            System.out.println("Cut the third wire.");
        }
    }

    @Override
    public void help() {
        System.out.println("(?) Type the abbreviations of each color from top to bottom\n" + 
                        "    > Eg: red white yellow black blue\n");
    }

    public static void main(String[] args) {
        Module wires = new Wires("On the Subject of Wires");
        Scanner scanner = new Scanner(System.in);
        wires.run(scanner);
        scanner.close();
    }

    
}