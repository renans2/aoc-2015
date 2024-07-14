package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class CustomFileReader implements ICustomFileReader {
    private final File file;
    private Scanner sc;
    private int numberOfLines;
    private final String[] lines;

    public CustomFileReader(String filePath) throws FileNotFoundException {
        file = new File(filePath);
        sc = new Scanner(file);
        setNumberOfLines();
        lines = new String[numberOfLines];
        setLines();
    }

    @Override
    public String getLine(int index) {
        if(index >= numberOfLines) {
            throw new IndexOutOfBoundsException("Index is greater than the number of lines");
        } else {
            return lines[index];
        }
    }

    @Override
    public int getNumberOfLines(){
        return numberOfLines;
    }

    @Override
    public String[] lines() {
        return Arrays.copyOf(lines, numberOfLines);
    }

    private void setLines() {
        for (int i = 0; i < lines.length; i++)
            lines[i] = sc.nextLine();
    }

    private void setNumberOfLines() throws FileNotFoundException {
        while(sc.hasNextLine()) {
            sc.nextLine();
            numberOfLines++;
        }

        reset();
    }

    private void reset() throws FileNotFoundException {
        sc = new Scanner(file);
    }
}
