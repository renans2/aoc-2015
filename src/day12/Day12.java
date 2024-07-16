package day12;

import util.CustomFileReader;
import java.io.FileNotFoundException;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class Day12 {
    public static void main(String[] args) throws FileNotFoundException {
        String jsonInput = new CustomFileReader("src/day12/input.txt").getLine(0);

        // Part 1
        int sum = sumAllNumbers(jsonInput);
        System.out.println(sum);
    }

    private static int sumAllNumbers(String json) {
        int sum = 0;
        String[] numbers = json.split("[\\[\\](){};, \":a-zA-Z]+");

        for (String number : numbers) {
            if(!number.isEmpty())
                sum += Integer.parseInt(number);
        }

        return sum;
    }
}
