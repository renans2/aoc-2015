package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CustomFileReader implements ICustomFileReader {
    private int currentLine = 0;
    private final File file;
    private Scanner sc;
    private int numberOfLines = 0;

    public CustomFileReader(String filePath) throws FileNotFoundException {
        file = new File(filePath);
        sc = new Scanner(file);
        numberOfLines = countLines();
    }

    @Override
    public String getLine(int index) throws FileNotFoundException {
        if(index < currentLine)
            reset();

        while (currentLine < index && sc.hasNextLine())
            skipLine();

        return sc.hasNextLine() ? sc.nextLine() : "***There is no such line";
    }

    @Override
    public int getNumberOfLines(){
        return numberOfLines;
    }

    private void reset() throws FileNotFoundException {
        sc = new Scanner(file);
        currentLine = 0;
    }

    private void skipLine() {
        sc.nextLine();
        currentLine++;
    }

    private int countLines() throws FileNotFoundException {
        int lines = 0;

        while(sc.hasNextLine()) {
            skipLine();
            lines++;
        }

        reset();
        return lines;
    }
}
