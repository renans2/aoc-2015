package day7;

import util.CustomFileReader;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class Day7 {
    private static final String PATH = "src/day7/input.txt";
    private static final String DIGITS = "0123456789";
    private final Map<String, Integer> wires;
    private final List<Integer> processedLines;
    private final String[] lines;

    public static void main(String[] args) throws FileNotFoundException {
        Day7 instance = new Day7();

        // Part 1
        int signalOfA = instance.getSignal("a");
        System.out.println(signalOfA);

        // Part 2
        instance.resetWith("b", signalOfA);
        int signalOfA2 = instance.getSignal("a");
        System.out.println(signalOfA2);
    }

    public Day7() throws FileNotFoundException {
        wires = new HashMap<>();
        processedLines = new ArrayList<>();
        lines = new CustomFileReader(PATH).lines();
        processSignalAttributions();
        processOtherAttributions();
    }

    private int getSignal(String identifier) {
        return wires.get(identifier);
    }

    private void processSignalAttributions() {
        for (int i = 0; i < lines.length; i++) {
            String[] splittedLine = lines[i].split(" ");
            if(isSignalAttribution(splittedLine)){
                registerSignalAttribution(splittedLine);
                processedLines.add(i);
            }
        }
    }

    private void processOtherAttributions(){
        while(processedLines.size() < lines.length){
            for(int i = 0; i < lines.length; i++){
                if(!processedLines.contains(i)){
                    String[] splittedLine = lines[i].split(" ");

                    if(isValidWireAttribution(splittedLine)){
                        makeWireAttribution(splittedLine);
                        processedLines.add(i);
                    }
                    else if(isValidDualGateAttribution(splittedLine)){
                        makeDualGateAttribution(splittedLine);
                        processedLines.add(i);
                    }
                    else if(isValidNotGateAttribution(splittedLine)){
                        makeNotGateAttribution(splittedLine);
                        processedLines.add(i);
                    }
                    else if(isValidShiftAttribution(splittedLine)){
                        makeShiftAttribution(splittedLine);
                        processedLines.add(i);
                    }
                }
            }
        }
    }

    private boolean isSignalAttribution(String[] line) {
        return fieldIsNumber(line[0]) && line[1].equals("->");
    }

    private void registerSignalAttribution(String[] line) {
        if(!wires.containsKey(line[2])){
            wires.put(line[2], Integer.parseInt(line[0]));
        }
    }

    private boolean isValidWireAttribution(String[] line) {
        return wires.containsKey(line[0]) && line[1].equals("->");
    }

    private void makeWireAttribution(String[] line) {
        wires.put(line[2], wires.get(line[0]));
    }

    private boolean isValidDualGateAttribution(String[] line) {
        String operatorField = line[1];
        return (operatorField.equals("AND") || operatorField.equals("OR")) &&
               (wires.containsKey(line[0])  || fieldIsNumber(line[0]))     &&
                wires.containsKey(line[2]);
    }

    private void makeDualGateAttribution(String[] line) {
        if(fieldIsNumber(line[0]))
            wires.put(line[4], getOperation(line[1], Integer.parseInt(line[0]), wires.get(line[2])));
        else
            wires.put(line[4], getOperation(line[1], wires.get(line[0]), wires.get(line[2])));
    }

    private boolean isValidNotGateAttribution(String[] line) {
        return line[0].equals("NOT") && wires.containsKey(line[1]);
    }

    private void makeNotGateAttribution(String[] line) {
        wires.put(line[3], ~wires.get(line[1]));
    }

    private boolean isValidShiftAttribution(String[] line) {
        return (line[1].equals("RSHIFT") || line[1].equals("LSHIFT")) && wires.containsKey(line[0]);
    }

    private void makeShiftAttribution(String[] line) {
        int result;

        if(line[1].equals("RSHIFT")){
            result = wires.get(line[0]) >> Integer.parseInt(line[2]);
        } else
            result = wires.get(line[0]) << Integer.parseInt(line[2]);

        wires.put(line[4], result);
    }

    private boolean fieldIsNumber(String field) {
        return DIGITS.contains(String.valueOf(field.charAt(0)));
    }

    private static int getOperation(String operator, int wire1, int wire2) {
        if(operator.equals("AND"))
            return wire1 & wire2;
        else
            return wire1 | wire2;
    }

    private void resetWith(String identifier, int signalValue) {
        wires.clear();
        wires.put(identifier, signalValue);
        processedLines.clear();
        processSignalAttributions();
        processOtherAttributions();
    }
}
