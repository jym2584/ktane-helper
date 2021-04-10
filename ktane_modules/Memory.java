package ktane_modules;
import java.util.*;

public class Memory extends Module {

    protected Memory(String name) {
        super(name);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void run(Scanner scanner) {
        int stageNum = 1;
        while(true) {
            System.out.print(">> What is the current display number? (Stage " + stageNum + "): ");
            String input = scanner.nextLine().toLowerCase();
            Map<String, Integer> stage = new LinkedHashMap();

            try {
                if (input.equals("help")) {
                    help();
                
                } else if (input.equals("quit")) {
                    break;

                } else if (displayNumberIsInRange(Integer.parseInt(input))) { // if the number is between 1 and 4
                    while(true) {
                        if(stageNum == 1) {
                            System.out.println( "Stage 1 info:\n" +
                                "If the display is 1, press the button in the second position.\n" +
                                "If the display is 2, press the button in the second position.\n" +
                                "If the display is 3, press the button in the third position.\n" +
                                "If the display is 4, press the button in the fourth position.\n");

                            System.out.print(">> Ask the defuser the number that corresponds with the position: ");
                            Integer inputInt = Integer.parseInt(scanner.nextLine());
                            if(displayNumberIsInRange(inputInt)) {
                                stage.put("STAGE_1_POSITION", inputInt);
                                stageNum++;
                            }
                        } else if (stageNum == 2) {
                            System.out.println( "Stage 2 info:\n" +
                            "If the display is 1, press the button labeled '4'.\n" +
                            String.format("If the display is 2, press the button in the same position that [%d] is in from stage 1.%n", stage.get("STAGE_1_POSITION")) +
                            "If the display is 3, press the button in the first position.\n" +
                            String.format("If the display is 4, press the button in the same position that [%d] is in from stage 1.", stage.get("STAGE_1_POSITION"))
                            );
                            
                            Integer inputInt = Integer.parseInt(scanner.nextLine());
                        }



                    }







                } else {
                    System.out.println("Enter a valid display number between 1 and 4.\n");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("That doesn't seem like a valid display number");
                help();
            }
        }

    }

    @Override
    public void help() {
        System.out.println("Enter a display number between 1 and 4.\n" +
        "   > The program will assist you with storing information that corresponds to the current stage.\n");
    }

    /**
     * Helper function to determine if the display number is currently within the range 1-4.
     * @param num
     * @return
     */
    public boolean displayNumberIsInRange(int num) {
        return num > 0 && num < 5;
    }

    /**
     * Helper function that prints out the saved stage information
     */
    public static void printStages(Map<String, Integer> stage) {
        stage.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        });
    }

    public static void main(String[] args) {
        Module wires = new Memory("On the Subject of Memory");
        Scanner scanner = new Scanner(System.in);
        wires.run(scanner);
        scanner.close();
    }
    
}
