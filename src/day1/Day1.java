package day1;
import util.*;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class Day1 {
    public static void main(String[] args) throws Exception {

        ICustomFileReader fileReader = new CustomFileReader("src/day1/input.txt");
        char[] instructionsChars = fileReader.getLine(0).toCharArray();

        // Part 1
        int floor = getFloor(instructionsChars);
        System.out.println(floor);

        // Part 2
        int positionForBasement = getPositionForBasement(instructionsChars);
        System.out.println(positionForBasement);
    }

    private static int getFloor(char[] instructions) {
        int floor = 0;

        for(char inst : instructions)
            floor += processFloor(inst);

        return floor;
    }

    private static int getPositionForBasement(char[] instructions) throws Exception {
        int position = 1;
        int floor = 0;

        for(char inst : instructions) {
            floor += processFloor(inst);
            if (floor == -1)
                return position;
            else
                position++;
        }

        throw new Exception("the Santa never goes to the basement");
    }

    private static int processFloor(char instruction) {
        if(instruction == '(')
            return 1;
        else
            return -1;
    }
}
