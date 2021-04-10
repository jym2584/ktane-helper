package ktane_modules;
import java.util.*;
/**
 * Memory module
 */
public class Memory extends Module {

    public Memory(String name) {
        super(name);
        //TODO Auto-generated constructor stub
    }

    /**
     * Driver code
     */
    @Override
    public void run(Scanner scanner) {
        while(true) {
            int stageNum = 1; // Will switch back to the first stage if an incorrect button was pressed
            Map<String, Integer> stage = new LinkedHashMap<>();
            System.out.print(">> What is the current display number? (Stage " + stageNum + "): ");
            String input = scanner.nextLine().toLowerCase();

            try {
                if (input.equals("help")) {
                    help();
                
                } else if (input.equals("quit")) {
                    break;

                } else if (displayNumberIsInRange(Integer.parseInt(input))) { // if the number is between 1 and 4
                    while(true) { // Not really sure how to make the statements below the loop more efficient. Perhaps making a separate helper function that prints the stage info?
                        if(stageNum == 1) { // First Stage
                            System.out.println(
                                "\nSTAGE 1 INFO:\n" +
                                "---------------------------------------------------------------------------------\n" +
                                "   > If the display is 1, tell the defuser to press the button in the second position.\n" +
                                "   > If the display is 2, tell the defuser to press the button in the second position.\n" +
                                "   > If the display is 3, tell the defuser to press the button in the third position.\n" +
                                "   > If the display is 4, tell the defuser to press the button in the fourth position.\n"
                            );

                            System.out.print(">> Ask the defuser the number as well as the button position that number is in (eg: 2 4): ");
                            String input2 = scanner.nextLine();
                            String tokens[] = input2.split(" ");
                            if( displayNumberIsInRange(Integer.parseInt(tokens[0])) && displayNumberIsInRange(Integer.parseInt(tokens[1]))) {
                                stage.put("STAGE_1_NUMBER", Integer.parseInt(tokens[0]));
                                stage.put("STAGE_1_POSITION", Integer.parseInt(tokens[1]));
                                stageNum++;
                            }

                        } else if (stageNum == 2) { // Second stage
                            System.out.println(
                                "\nSTAGE 2 INFO:\n" +
                                "---------------------------------------------------------------------------------\n" +
                                              "   > If the display is 1, tell the defuser to press the button labeled '4'.\n" +
                                String.format("   > If the display is 2, tell the defuser to press the button in the same position that [%d] is in from stage 1.%n", stage.get("STAGE_1_POSITION")) +
                                              "   > If the display is 3, tell the defuser to press the button in the first position.\n" +
                                String.format("   > If the display is 4, tell the defuser to press the button in the same position that [%d] is in from stage 1.", stage.get("STAGE_1_POSITION"))
                            );
                            printStages(stage);
                            
                            System.out.print(">> Ask the defuser the number as well as the button position that number is in (eg: 2 4): ");
                            String input2 = scanner.nextLine();
                            String tokens[] = input2.split(" ");

                            if( displayNumberIsInRange(Integer.parseInt(tokens[0])) && displayNumberIsInRange(Integer.parseInt(tokens[1]))) {
                                stage.put("STAGE_2_NUMBER", Integer.parseInt(tokens[0]));
                                stage.put("STAGE_2_POSITION", Integer.parseInt(tokens[1]));
                                stageNum++;
                            }
                            
                        } else if (stageNum == 3) { // Third stage
                            System.out.println(
                            "\nSTAGE 3 INFO:\n" +
                                "---------------------------------------------------------------------------------\n" +
                                String.format("   > If the display is 1, tell the defuser to press the button that has the number [%d] from stage 2.%n", stage.get("STAGE_2_NUMBER")) +
                                String.format("   > If the display is 1, tell the defuser to press the button that has the number [%d] from stage 1.%n", stage.get("STAGE_1_NUMBER")) +
                                              "   > If the display is 3, press the button in the third position.\n" +
                                              "   > If the display is 4, press the button labeled '4'."
                            );
                            printStages(stage);

                            System.out.print(">> Ask the defuser the number of the button: ");
                            String input2 = scanner.nextLine();

                            if( displayNumberIsInRange(Integer.parseInt(input2)) ) {
                                stage.put("STAGE_3_NUMBER", Integer.parseInt(input2));
                                stageNum++;
                            }

                        } else if (stageNum == 4) { // Fourth stage
                            System.out.println(
                                "\nSTAGE 4 INFO:\n" +
                                "---------------------------------------------------------------------------------\n" +
                                String.format("   > If the display is 1, tell the defuser to press the button in the same position that [%d] is in from stage 1.%n", stage.get("STAGE_1_POSITION")) +
                                              "   > If the display is 2, press the button in the first position." + 
                                String.format("   > If the display is 3, tell the defuser to press the button in the same position that [%d] is in from stage 2.%n", stage.get("STAGE_2_POSITION")) +
                                String.format("   > If the display is 4, tell the defuser to press the button in the same position that [%d] is in from stage 2.", stage.get("STAGE_2_POSITION"))
                            );
                            printStages(stage);

                            System.out.print(">> Ask the defuser the number of the button: ");
                            String input2 = scanner.nextLine();

                            if( displayNumberIsInRange(Integer.parseInt(input2)) ) {
                                stage.put("STAGE_4_NUMBER", Integer.parseInt(input2));
                                stageNum++;
                            }

                        } else if (stageNum == 5) { // Fifth Stage
                            System.out.println(
                                "\nSTAGE 5 INFO:\n" +
                                "---------------------------------------------------------------------------------\n" +
                                String.format("   > If the display is 1, tell the defuser to press the button that has the number [%d] from stage 1.%n", stage.get("STAGE_1_NUMBER")) +
                                String.format("   > If the display is 2, tell the defuser to press the button that has the number [%d] from stage 2.%n", stage.get("STAGE_2_NUMBER")) +
                                String.format("   > If the display is 3, tell the defuser to press the button that has the number [%d] from stage 4.%n", stage.get("STAGE_4_NUMBER")) +
                                String.format("   > If the display is 4, tell the defuser to press the button that has the number [%d] from stage 3.", stage.get("STAGE_3_NUMBER"))
                            );
                            printStages(stage);

                            System.out.print("Enter any input to break this part of the program once you're done!");
                            String input2 = scanner.nextLine();
                            break;
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
     * @return returns true if the number is between 1-4
     */
    private boolean displayNumberIsInRange(int num) {
        return num > 0 && num < 5;
    }

    /**
     * Helper function that prints out the saved stage information
     */
    private static void printStages(Map<String, Integer> stage) {
        System.out.println("\n" + String.format("%-20s %s", "TYPE", "NUMBER"));
        System.out.println("----------------------------");
        stage.entrySet().forEach(entry -> {
            System.out.println(String.format("%-23s %s", entry.getKey(), entry.getValue()));
        });
        System.out.println();
    }

    public static void main(String[] args) {
        Module wires = new Memory("On the Subject of Memory");
        Scanner scanner = new Scanner(System.in);
        wires.run(scanner);
        scanner.close();
    }
    
}
