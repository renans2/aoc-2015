package util;

import java.io.FileNotFoundException;

public interface ICustomFileReader {
    String getLine(int index);
    int getNumberOfLines();
    String[] lines() throws FileNotFoundException;
}
