package day10;

import java.io.FileNotFoundException;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class Day10 {
    public static void main(String[] args) throws FileNotFoundException {
        String input = "1113222113";

        // Part 1
        int length = lookAndSayLength(input, 40);
        System.out.println(length);

        // Part 2
        int length2 = lookAndSayLength(input, 50);
        System.out.println(length2);
    }

    private static int lookAndSayLength(String input, int depth) {
        // Recursive case
        if(depth > 0) {
            StringBuilder newInput = new StringBuilder();
            int i = 0;
            while(i < input.length()) {
                char number = input.charAt(i);
                int counter = 1;
                int j = i + 1;
                while(j < input.length() && input.charAt(j) == number) {
                    counter++;
                    j++;
                }
                i = j;
                newInput.append(counter);
                newInput.append(number);
            }
            return lookAndSayLength(newInput.toString(), depth - 1);
        }
        // Base case
        else {
            return input.length();
        }
    }
}
