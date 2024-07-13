package day3;

import util.CustomFileReader;
import util.ICustomFileReader;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class Day3 {
    public static void main(String[] args) throws FileNotFoundException {
        ICustomFileReader fileReader = new CustomFileReader("src/day3/input.txt");
        String input = fileReader.getLine(0);

        // Part 1
        int housesWithPresents = processHouses(input);
        System.out.println(housesWithPresents);

        // Part 2
        int housesWithPresents2 = processHousesWithRobot(input);
        System.out.println(housesWithPresents2);
    }

    private static int processHouses(String input) {
        int[] currentPos = {0, 0};
        Set<House> set = new HashSet<>();
        set.add(new House(currentPos));

        for(char direction : input.toCharArray()) {
            processDirection(direction, currentPos);
            set.add(new House(currentPos));
        }

        return set.size();
    }

    private static int processHousesWithRobot(String input) {
        int[] currentPosSanta = {0, 0};
        int[] currentPosRobot = {0, 0};
        Set<House> santaSet = new HashSet<>();
        Set<House> robotSet = new HashSet<>();
        santaSet.add(new House(currentPosSanta));
        robotSet.add(new House(currentPosRobot));

        for (int i = 0; i < input.length(); i++) {
            if(i % 2 == 0) {
                processDirection(input.charAt(i), currentPosSanta);
                santaSet.add(new House(currentPosSanta));
            } else {
                processDirection(input.charAt(i), currentPosRobot);
                robotSet.add(new House(currentPosRobot));
            }
        }

        santaSet.addAll(robotSet);

        return santaSet.size();
    }

    private static void processDirection(char direction, int[] currentPos) {
        switch (direction) {
            case '<': currentPos[0]--; break;
            case '>': currentPos[0]++; break;
            case 'v': currentPos[1]--; break;
            case '^': currentPos[1]++; break;
            default: break;
        }
    }
}
