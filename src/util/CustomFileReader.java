package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
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

        if(sc.hasNextLine())
            return sc.nextLine();
        else
            throw new NoSuchElementException("There is no next line");
    }

    @Override
    public int getNumberOfLines(){
        return numberOfLines;
    }

    @Override
    public String[] lines() throws FileNotFoundException {
        String[] lines = new String[numberOfLines];

        for (int i = 0; i < lines.length; i++)
            lines[i] = sc.nextLine();

        reset();
        return lines;
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
