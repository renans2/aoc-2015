package day3;

import java.util.Arrays;

public class House {
    private final int[] coords;

    public House(int[] coordinates) {
        coords = Arrays.copyOf(coordinates, coordinates.length);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(coords);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return Arrays.equals(coords, house.coords);
    }
}
