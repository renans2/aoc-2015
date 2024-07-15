package day8;

import util.CustomFileReader;
import util.ICustomFileReader;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.function.Function;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class Day8 {
    public static void main(String[] args) throws FileNotFoundException {
        ICustomFileReader fileReader = new CustomFileReader("src/day8/input.txt");
        String[] lines = fileReader.lines();
        
        Function<String, Integer> getNumStringChars = (str) -> {
            String subStr = str.substring(1, str.length() - 1);
            int counter = subStr.length();
            int i = 0;

            while(i < subStr.length()) {
                if(subStr.charAt(i) == '\\' && i+1 < subStr.length()) {
                    char nextChar = subStr.charAt(i+1);
                    if(nextChar == '\\' || nextChar == '\"') { counter--; i += 2; }
                    else if(nextChar == 'x' && i+3 < subStr.length()){ counter -= 3; i += 4; }
                    else i++;
                } else i++;
            }

            return counter;
        };

        Function<String, Integer> getNumEncodedStringChars = (str) -> {
            int counter = str.length();
            int i = 0;

            while(i < str.length()) {
                if(str.charAt(i) == '\\' || str.charAt(i) == '\"')
                    counter++;
                i++;
            }

            return counter + 2;
        };

        // Part 1
        Integer result = Arrays.stream(lines)
                               .map(str -> str.length() - getNumStringChars.apply(str))
                               .reduce(0, Integer::sum);
        System.out.println(result);

        // Part 2
        Integer result2 = Arrays.stream(lines)
                                .map(str -> getNumEncodedStringChars.apply(str) - str.length())
                                .reduce(0, Integer::sum);
        System.out.println(result2);
    }
}
