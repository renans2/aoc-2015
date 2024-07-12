package util;

import java.io.FileNotFoundException;

public interface ICustomFileReader {
    String getLine(int index) throws FileNotFoundException;
    int getNumberOfLines();
}
