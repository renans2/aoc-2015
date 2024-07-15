package day9;

import util.CustomFileReader;
import util.ICustomFileReader;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class Day9 {
    private static final String PATH = "src/day9/input.txt";
    private final String[] lines;
    private final Set<String> locations;
    private final Map<PairOfLocations, Integer> distances;
    private final String[] locationsArray;
    private final int shortestDistance;
    private final int longestDistance;

    public static void main(String[] args) throws FileNotFoundException {
        Day9 instance = new Day9();

        // Part 1
        System.out.println(instance.getShortestDistance());

        // Part 2
        System.out.println(instance.getLongestDistance());
    }

    public Day9() throws FileNotFoundException {
        lines = new CustomFileReader(PATH).lines();
        locations = new HashSet<>();
        distances = new HashMap<>();
        setLocationsAndDistances();
        locationsArray = locations.toArray(new String[0]);
        shortestDistance = getDistance("Shortest");
        longestDistance = getDistance("Longest");
    }

    private void setLocationsAndDistances() {
        Arrays.stream(lines).forEach(line -> {
            String[] splitted = line.split(" ");
            String location1 = splitted[0];
            String location2 = splitted[2];
            int distance = Integer.parseInt(splitted[4]);

            locations.add(location1);
            locations.add(location2);
            distances.put(new PairOfLocations(location1, location2), distance);
        });
    }

    private int getDistance(String type) {
        List<Integer> availableIndexes = new ArrayList<>();
        for (int i = 0; i < locationsArray.length; i++) {
            availableIndexes.add(i);
        }

        return getDistanceRec(new ArrayList<>(), availableIndexes, type);
    }

    private int getDistanceRec(List<String> path, List<Integer> availableIndexes, String type) {
        // Base case
        if(availableIndexes.isEmpty()){
            int pathDistance = 0;
            for (int i = 0; i < path.size() - 1; i++) {
                String location1 = path.get(i);
                String location2 = path.get(i + 1);
                pathDistance += distances.get(new PairOfLocations(location1, location2));
            }
            return pathDistance;
        }
        // Recursive case
        else {
            int finalDistance = -1;

            for (int i = 0; i < availableIndexes.size(); i++) {
                Integer arrayIdx = availableIndexes.get(i);
                String location = locationsArray[arrayIdx];

                List<String> newPath = new ArrayList<>(path);
                newPath.add(location);

                List<Integer> newIndexes = new ArrayList<>(availableIndexes);
                newIndexes.remove(arrayIdx);

                int distance = getDistanceRec(newPath, newIndexes, type);

                if(finalDistance == -1) {
                    finalDistance = distance;
                } else if(type.equals("Shortest") && distance < finalDistance) {
                    finalDistance = distance;
                } else if(type.equals("Longest") && distance > finalDistance) {
                    finalDistance = distance;
                }
            }

            return finalDistance;
        }
    }

    private int getShortestDistance() {
        return shortestDistance;
    }

    private int getLongestDistance() {
        return longestDistance;
    }
}
