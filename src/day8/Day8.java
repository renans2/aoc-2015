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

        Integer codeChars = Arrays.stream(lines)
                                  .map(String::length)
                                  .reduce(0, Integer::sum);

        Integer stringChars = Arrays.stream(lines)
                                    .map(getNumStringChars)
                                    .reduce(0, Integer::sum);

        System.out.println(codeChars - stringChars);
    }
}
