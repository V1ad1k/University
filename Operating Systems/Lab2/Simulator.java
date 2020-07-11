package Task2;

import java.util.ArrayList;

public class Simulator {
    // Copies are needed in order to reset the simulator.
    private ArrayList<Integer> toProcess;       // Processes to be evaluated
    private ArrayList<Integer> toProcessCopy;
    private ArrayList<Integer> deadlines;       // Deadlines - needed in EDF and FD-SCAN
    private ArrayList<Integer> deadlinesCopy;
    private boolean hasDeadlines;
    private ArrayList<Integer> processed = new ArrayList<>();
    private Disk disk;                          // Representation of a disk
    private Disk diskCopy;
    private int totalMovements = 0;             // Disk head movements in the process of simulation
 
    public Simulator(ArrayList<Integer> toProcess, Disk disk, ArrayList<Integer> deadlines) {
        this.toProcess = toProcess;
        this.toProcessCopy = new ArrayList<>(toProcess);
        this.disk = disk;
        this.deadlines = deadlines;
        this.deadlinesCopy = new ArrayList<>(deadlines);
        this.hasDeadlines = true;
        try {
            this.diskCopy = (Disk) disk.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            // Should never be reached.
        }
    }
 
    public Simulator(ArrayList<Integer> toProcess, Disk disk) {
        this.toProcess = toProcess;
        this.toProcessCopy = new ArrayList<>(toProcess);
        this.disk = disk;
        try {
            this.diskCopy = (Disk) disk.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        this.hasDeadlines = false;
    }
 
    public int getTotalMovements() {
        return totalMovements;
    }
 
    public ArrayList<Integer> getProcessed() {
        return processed;
    }
 
    public boolean doesHaveDeadlines() {
        return hasDeadlines;
    }
 
    public void simulate(SortingMode mode) {
        while (toProcess.size() > 0) {
            // Keeps evaluating while there's still something left to evaluate.
            int index = -1;
            Selector selector = new Selector();
            // Switch-case to pick the appropriate selector's method
            switch (mode) {
                case FCFS:
                    index = selector.fcfs();
                    break;
                case SCAN:
                    index = selector.scan(toProcess, disk);
                    break;
                case SSTF:
                    index = selector.sstf(toProcess, disk);
                    break;
                case CSCAN:
                    index = selector.cscan(toProcess, disk);
                    break;
                case EDF:
                    if (deadlines == null) {
                        // Sanity check. Cannot simulate EDF with no deadlines passed.
                        throw new NullPointerException();
                    }
                    index = selector.edf(deadlines);
                    break;
                case FDSCAN:
                    if (deadlines == null) {
                        // Sanity check. Cannot simulate FD-SCAN with no deadlines passed.
                        throw new NullPointerException();
                    }
                    index = selector.fdscan(toProcess, disk, deadlines);
                    break;
            }
            int pos = toProcess.get(index);             // Get the cylinder's index
            totalMovements += disk.posDiff(pos);        // Add the difference of positions to total movements
            disk.setHeadPos(pos);                       // Set the head to its new position
            processed.add(pos);                         // Add the process to the list of evaluated processes
            toProcess.remove(toProcess.get(index));     // Remove it from processes to be evaluated
            if (deadlines != null) {
                deadlines.remove(deadlines.get(index)); // Do the same with a deadline
            }
        }
    }
 
    public void reset() {
        // Resets the simulator by:
        processed.clear();                              // - clearing the list of evaluated processes
        toProcess = new ArrayList<>(toProcessCopy);     // - setting toProcess to its copy
        try {
            disk = (Disk) diskCopy.clone();             // - setting disk to its copy
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            // Should never be reached.
        }
        if (deadlines != null) {
            deadlines = new ArrayList<>(deadlinesCopy); // - setting deadlines to its copy
        }
        totalMovements = 0;                             // - resetting the movements counter
    }
 
    @Override
    public String toString() {
        if (processed.size() == 0) {
            return "Call simulate() to proceed.";
        } else if (toProcess.size() != 0) {
            return "Simulation in progress.";
        } else {
            StringBuilder output = new StringBuilder("Order of completion:\n[");
            for (Integer i : processed) {
                output.append(i);
                output.append(", ");
            }
            output.setLength(output.length() - 2);
            output.append("]\n");
            output.append("Total movement of disk head: ").append(totalMovements);
            return output.toString();
        }
    }
}