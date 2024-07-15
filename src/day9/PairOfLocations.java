package day9;

public class PairOfLocations {
    private final String location1;
    private final String location2;

    public PairOfLocations(String location1, String location2) {
        this.location1 = location1;
        this.location2 = location2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PairOfLocations other = (PairOfLocations) o;
        return (location1.equals(other.location1) && location2.equals(other.location2)) ||
               (location1.equals(other.location2) && location2.equals(other.location1));
    }

    @Override
    public int hashCode() {
        return location1.hashCode() + location2.hashCode();
    }

    @Override
    public String toString() {
        return location1 + " " + location2;
    }
}
