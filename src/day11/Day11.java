package day11;

import java.io.FileNotFoundException;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class Day11 {
    public static void main(String[] args) throws FileNotFoundException {
        String input = "cqjxjnds";
        String validChars = "abcdefghijklmnopqrstuvwxyz";

        // Part 1
        String nextPassword = getNextPassword(input);
        System.out.println(nextPassword);

        // Part 2
        String nextNextPassword = getNextPassword(nextPassword);
        System.out.println(nextNextPassword);
    }

    private static String getNextPassword(String input) {
        String password = increment(input);

        while(!isValidPassword(password)) {
            password = increment(password);
        }

        return password;
    }

    private static String increment(String password) {
        String validChars = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder newPassword = new StringBuilder();

        int currentCharIdx = password.length() - 1;
        int nextValidCharIdx;

        do {
            char currentChar = password.charAt(currentCharIdx);
            nextValidCharIdx = (validChars.indexOf(currentChar) + 1) % validChars.length();
            newPassword.append(validChars.charAt(nextValidCharIdx));
            currentCharIdx--;
        } while(nextValidCharIdx == 0);

        for (int i = currentCharIdx; i >= 0; i--)
            newPassword.append(password.charAt(i));

        return newPassword.reverse().toString();
    }

    private static boolean isValidPassword(String password) {
        return hasOneIncreasingStraight(password) &&
               hasTwoPairs(password)              &&
               hasNoILO(password);
    }

    private static boolean hasOneIncreasingStraight(String password) {
        String validChars = "abcdefghijklmnopqrstuvwxyz";
        int i = 0;

        while (i < password.length() - 2) {
            String subStr = password.substring(i, i + 3);

            if (validChars.contains(subStr))
                return true;

            i++;
        }

        return false;
    }

    private static boolean hasTwoPairs(String password) {
        int counter = 0;
        int i = 0;

        while (i < password.length() - 1) {
            if(password.charAt(i) == password.charAt(i+1)) {
                if(i+2 >= password.length() || password.charAt(i) != password.charAt(i+2)) {
                    counter++;
                    i += 2;
                } else
                    i += 3;
            } else
                i++;
        }

        return counter >= 2;
    }

    private static boolean hasNoILO(String password) {
        for(char c : password.toCharArray()) {
            if(c == 'i' || c == 'l' || c == 'o')
                return false;
        }

        return true;
    }
}
