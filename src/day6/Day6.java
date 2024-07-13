package day6;

import util.CustomFileReader;
import util.ICustomFileReader;

import java.io.FileNotFoundException;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class Day6 {
    public static void main(String[] args) throws FileNotFoundException {
        ICustomFileReader fileReader = new CustomFileReader("src/day6/input.txt");
        String[] instructions = fileReader.lines();

        // Part 1
        boolean[][] lights = new boolean[1000][1000];
        int numLightsLit = countLightsLit(instructions, lights);
        System.out.println(numLightsLit);

        // Part 2
        int[][] brightness = new int[1000][1000];
        int totalBrightness = countTotalBrightness(instructions, brightness);
        System.out.println(totalBrightness);
    }

    private static int countLightsLit(String[] instructions, boolean[][] lights) {
        for(String instruction : instructions)
            processInstruction(instruction, lights);

        return countOnGrid(lights);
    }

    private static void processInstruction(String instruction, boolean[][] lights) {
        String[] words = instruction.split(" ");

        if(words[0].equals("turn")) {
            int[] c = getCoords(2, 4, words);

            if(words[1].equals("off"))
                turnOff(c[0], c[1], c[2], c[3], lights);
            else
                turnOn(c[0], c[1], c[2], c[3], lights);
        } else {
            int[] c = getCoords(1, 3, words);
            toggle(c[0], c[1], c[2], c[3], lights);
        }
    }

    private static void turnOff(int x1, int y1, int x2, int y2, boolean[][] lights) {
        for (int y = y1; y <= y2; y++)
            for (int x = x1; x <= x2; x++)
                lights[x][y] = false;
    }

    private static void turnOn(int x1, int y1, int x2, int y2, boolean[][] lights) {
        for (int y = y1; y <= y2; y++)
            for (int x = x1; x <= x2; x++)
                lights[x][y] = true;
    }

    private static void toggle(int x1, int y1, int x2, int y2, boolean[][] lights) {
        for (int y = y1; y <= y2; y++)
            for (int x = x1; x <= x2; x++)
                lights[x][y] = !lights[x][y];
    }

    private static int countOnGrid(boolean[][] lights) {
        int counter = 0;

        for (int i = 0; i < lights.length; i++)
            for (int j = 0; j < lights[i].length; j++)
                if (lights[i][j])
                    counter++;

        return counter;
    }

    private static int countTotalBrightness(String[] instructions, int[][] brightness) {
        for(String instruction : instructions)
            processBrightnessInstruction(instruction, brightness);

        return countBrightnessOnGrid(brightness);
    }

    private static void processBrightnessInstruction(String instruction, int[][] brightness) {
        String[] words = instruction.split(" ");

        if(words[0].equals("turn")) {
            int[] c = getCoords(2, 4, words);

            if(words[1].equals("off"))
                changeBrightness(c[0], c[1], c[2], c[3], -1, brightness);
            else
                changeBrightness(c[0], c[1], c[2], c[3], 1, brightness);
        } else {
            int[] c = getCoords(1, 3, words);
            changeBrightness(c[0], c[1], c[2], c[3], 2, brightness);
        }
    }

    private static void changeBrightness(int x1, int y1, int x2, int y2, int value, int[][] brightness) {
        for (int y = y1; y <= y2; y++)
            for (int x = x1; x <= x2; x++){
                brightness[x][y] += value;

                if(brightness[x][y] < 0)
                    brightness[x][y] = 0;
            }
    }

    private static int countBrightnessOnGrid(int[][] brightness) {
        int counter = 0;

        for (int i = 0; i < brightness.length; i++)
            for (int j = 0; j < brightness[i].length; j++)
                counter += brightness[i][j];

        return counter;
    }

    private static int[] getCoords(int coords1Index, int coords2Index, String[] words) {
        String[] coords1 = words[coords1Index].split(",");
        String[] coords2 = words[coords2Index].split(",");
        int x1 = Integer.parseInt(coords1[0]);
        int y1 = Integer.parseInt(coords1[1]);
        int x2 = Integer.parseInt(coords2[0]);
        int y2 = Integer.parseInt(coords2[1]);

        return new int[]{x1, y1, x2, y2};
    }
}
