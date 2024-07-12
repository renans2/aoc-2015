package day2;

import util.CustomFileReader;
import util.ICustomFileReader;

import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class Day2 {
    public static void main(String[] args) throws FileNotFoundException {
        ICustomFileReader fileReader = new CustomFileReader("src/day2/input.txt");
        String[] lines = fileReader.lines();

        // Part 1
        int totalWrappingPaper = getTotalWrappingPaper(lines);
        System.out.println(totalWrappingPaper);

        // Part 2
        int totalRibbon = getTotalRibbon(lines);
        System.out.println(totalRibbon);
    }

    private static int getTotalWrappingPaper(String[] lines) {
        int totalNeeded = 0;

        for(String line : lines)
            totalNeeded += processLineWrappingPaper(line);

        return totalNeeded;
    }

    private static int processLineWrappingPaper(String line) {
        int totalNeeded = 0;
        int[] smallestSide = {};
        int[][] sides = getSides(line);

        for(int[] side : sides){
            totalNeeded += 2 * side[0] * side[1];
            smallestSide = calcSmallestSide(side, smallestSide);
        }

        return totalNeeded + smallestSide[0] * smallestSide[1];
    }

    private static int getTotalRibbon(String[] lines) {
        int totalNeeded = 0;

        for(String line : lines)
            totalNeeded += processLineRibbon(line);

        return totalNeeded;
    }

    private static int processLineRibbon(String line) {
        int[] smallestSide = {};
        int[][] sides = getSides(line);

        for(int[] side : sides)
            smallestSide = calcSmallestSide(side, smallestSide);

        return getVolume(line) +
               2 * smallestSide[0] +
               2 * smallestSide[1];
    }

    private static int[][] getSides(String line) {
        int[] dimensions = getDimensions(line);
        int l = dimensions[0];
        int w = dimensions[1];
        int h = dimensions[2];

        return new int[][]{{l, w},
                           {l, h},
                           {w, h}};
    }

    private static int[] calcSmallestSide(int[] side, int[] currentSmallestSide) {
        int[] smallestSide = currentSmallestSide;

        if(currentSmallestSide.length == 0)
            smallestSide = side;
        else if(side[0] * side[1] < currentSmallestSide[0] * currentSmallestSide[1])
            smallestSide = side;

        return smallestSide;
    }

    private static int[] getDimensions(String line) {
        String[] dimensionsStr = line.split("x");
        return Arrays.stream(dimensionsStr)
                     .mapToInt(Integer::parseInt)
                     .toArray();
    }

    private static int getVolume(String line) {
        int[] dimensions = getDimensions(line);

        return dimensions[0] *
               dimensions[1] *
               dimensions[2];
    }
}
