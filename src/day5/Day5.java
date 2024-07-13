package day5;

import util.CustomFileReader;
import util.ICustomFileReader;

import java.io.FileNotFoundException;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class Day5 {
    public static void main(String[] args) throws FileNotFoundException {
        ICustomFileReader fileReader = new CustomFileReader("src/day5/input.txt");
        String[] strings = fileReader.lines();

        // Part 1
        int numNiceStrings = processStrings(strings);
        System.out.println(numNiceStrings);

        // Part 2
        int numNiceStrings2 = processStrings2(strings);
        System.out.println(numNiceStrings2);
    }

    private static int processStrings(String[] strings) {
        int niceCounter = 0;

        for (String string : strings)
            if(isNice(string))
                niceCounter++;

        return niceCounter;
    }

    private static boolean isNice(String string) {
        String naughtySubStrings = "ab, cd, pq, xy";
        return countVowels(string) >= 3 &&
               hasDoubleLetter(string)  &&
               !hasNaughtySubStrings(string, naughtySubStrings);
    }

    private static int countVowels(String string) {
        int counter = 0;
        String vowels = "aeiou";

        for (char c : string.toCharArray()) {
            if(vowels.contains(String.valueOf(c)))
                counter++;
        }

        return counter;
    }

    private static boolean hasDoubleLetter(String string) {
        for (int i = 0; i < string.length() - 1; i++) {
            if(string.charAt(i) == string.charAt(i + 1))
                return true;
        }

        return false;
    }

    private static boolean hasNaughtySubStrings(String string, String naughtySubStrings) {
        for (int i = 0; i < string.length() - 1; i++) {
            if(naughtySubStrings.contains(string.substring(i, i + 2)))
                return true;
        }

        return false;
    }

    private static int processStrings2(String[] strings) {
        int niceCounter = 0;

        for (String string : strings)
            if(isNice2(string))
                niceCounter++;

        return niceCounter;
    }

    private static boolean isNice2(String string) {
        return hasRepeatingPair(string) &&
               hasDoubleLetterSeparated(string);
    }

    private static boolean hasRepeatingPair(String string) {
        String[] pairs = new String[string.length() - 1];

        for (int i = 0; i < string.length() - 1; i++)
            pairs[i] = string.substring(i, i + 2);

        for (int i = 0; i < pairs.length - 1; i++)
            for (int j = i + 2; j < pairs.length; j++)
                if(pairs[i].equals(pairs[j]))
                    return true;

        return false;
    }

    private static boolean hasDoubleLetterSeparated(String string) {
        for (int i = 0; i < string.length() - 2; i++)
            if(string.charAt(i) == string.charAt(i + 2))
                return true;

        return false;
    }
}
