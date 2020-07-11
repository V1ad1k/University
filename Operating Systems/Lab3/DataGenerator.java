package Task2;

import java.util.*;

public class DataGenerator {
 
    public static HashMap<String, Integer> printSimulations(Simulator simulator, boolean hasDeadlines) {
        HashMap<String, Integer> results = new HashMap<>();
        for (SortingMode mode : SortingMode.values()) {
            System.out.println("\n" + mode.name() + ":");
            if (!hasDeadlines && (mode == SortingMode.EDF || mode == SortingMode.FDSCAN)) {
                System.out.print("not simulated.\n");
            } else {
                simulator.simulate(mode);
                System.out.println(simulator);
                results.put(mode.name(), simulator.getTotalMovements());
                simulator.reset();
            }
        }
        return results;
    }
 
    public static void printRanking(HashMap<String, Integer> results) {
        HashMap<String, Integer> resultsCopy = new HashMap<>(results);
        int counter = 1;
        int smallestValue = Integer.MAX_VALUE;
        int smallestCopy = Integer.MAX_VALUE;
        String smallestMode = "";
        System.out.println("\nFinal results:");
        while (resultsCopy.size() > 0) {
            for (HashMap.Entry<String, Integer> entry : resultsCopy.entrySet()) {
                String key = entry.getKey();
                int value = entry.getValue();
                if (value < smallestValue) {
                    smallestValue = value;
                    smallestMode = key;
                }
            }
            if (counter == 1) {
                smallestCopy = smallestValue;
            }
            String output = counter + ". " + smallestMode + ": \t" + smallestValue + " disk head movements";
            if (counter > 1) {
                double ratio = 100.00 * (double) (smallestValue - smallestCopy) / (double) smallestValue;
                output += String.format(Locale.US, " \t\t(%.4f%% worse)", ratio);
            }
            counter++;
            System.out.println(output);
            resultsCopy.remove(smallestMode);
            smallestValue = Integer.MAX_VALUE;
            smallestMode = "";
        }
    }
 
    public static Simulator fullUserPrompt(Scanner scanner, Disk disk) {
        ArrayList<Integer> processes = new ArrayList<>();
        boolean hasDeadlines = false;
        ArrayList<Integer> deadlines = new ArrayList<>();
        Simulator simulator;
 
        System.out.println("Input your request sequence. Non-integer to break.");
        while (scanner.hasNextInt()) {
            int current = scanner.nextInt();
            if (current > disk.getBound() || current < 0) {
                System.out.println("Keep your requests inside the boundaries.");
            } else {
                processes.add(current);
            }
        }
 
        String dirtyFix = scanner.next();
        System.out.println("Do you wish to insert deadlines for EDF/FDSCAN? y/n");
        if (scanner.next().equalsIgnoreCase("y")) {
            hasDeadlines = true;
            for (Integer process : processes) {
                System.out.println(process + ": ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Input valid integer.");
                }
                deadlines.add(scanner.nextInt());
            }
        }
 
        if (hasDeadlines) {
            simulator = new Simulator(processes, disk, deadlines);
        } else {
            simulator = new Simulator(processes, disk);
        }
 
        return simulator;
    }
 
    public static Simulator randomRequest(int bound, int howMany, Disk disk) {
        ArrayList<Integer> processes = new ArrayList<>();
        ArrayList<Integer> deadlines = new ArrayList<>();
        Random generator = new Random();
        for (int i = 0; i < howMany; i++) {
            int place = generator.nextInt(disk.getBound());
            if (processes.contains(place)){
                i--;
            }
            else {
                processes.add(generator.nextInt(place));
                deadlines.add(generator.nextInt(bound + 1));
            }
        }
        return new Simulator(processes, disk, deadlines);
    }
 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Simulator simulator;
        int cylindersNo;
        int initialPos;
        HashMap<String, Integer> results;
 
        System.out.print("Input the number of cylinders: ");
        while (true) {
            if (scanner.hasNextInt()) {
                cylindersNo = scanner.nextInt();
                if (cylindersNo < 1) {
                    System.out.println("Input a positive integer.");
                } else {
                    break;
                }
            } else {
                System.out.println("Input a valid number.");
            }
        }
 
        System.out.print("Input the initial position: ");
        while (true) {
            if (scanner.hasNextInt()) {
                initialPos = scanner.nextInt();
                if (initialPos < 0 || initialPos > cylindersNo) {
                    System.out.println("Keep your input between boundaries.");
                } else {
                    break;
                }
            } else {
                System.out.println("Input a valid number.");
            }
        }
 
        Disk disk = new Disk(initialPos, cylindersNo);
 
        System.out.println("Do you wish to:\n1. Generate random data\n2. Input your own data");
        int choice = -1;
        while (scanner.hasNextInt()) {
            choice = scanner.nextInt();
            if (choice < 1 || choice > 2) {
                System.out.println("Input a valid choice.");
            } else {
                break;
            }
        }
        if (choice == 1) {
            System.out.print("What's the maximum deadline time? ");
            int bound = scanner.nextInt();
            System.out.print("How many processes? ");
            int howMany = scanner.nextInt();
            simulator = randomRequest(bound, howMany, disk);
        } else if (choice == 2) {
            simulator = fullUserPrompt(scanner, disk);
        } else {
            simulator = new Simulator(new ArrayList<>(), disk);
        }
        scanner.close();
 
        results = printSimulations(simulator, simulator.doesHaveDeadlines());
        printRanking(results);
    }
}