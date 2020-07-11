package Task5;

import java.util.ArrayList;
import java.util.Iterator;

public class Processor {
    private final int id;
    private final int threshold;
    private final ArrayList<Process> heldProcesses;
    private int currentTick;

    public Processor(int id, int threshold) {
        this.id = id;
        this.threshold = threshold;
        this.heldProcesses = new ArrayList<>();
        this.currentTick = 0;
    }

    public int getId() {
        return id;
    }

    public int getCurrentLoad() {
        int counter = 0;
        for (Process process : heldProcesses) {
            counter += process.getLoad();
        }
        return counter;
    }

    public int getThreshold() {
        return threshold;
    }

    public void updateTick() {
        this.currentTick++;
    }

    public int removeFinished() {
        int counter = 0;
        Iterator<Process> i = heldProcesses.iterator();
        while (i.hasNext()) {
            Process process = i.next();
            if (process.getStartsAtTick() + process.getDuration() <= this.currentTick) {
                counter++;
                i.remove();
            }
        }
        return counter;
    }

    public void insert(Process process) {
        this.heldProcesses.add(process);
    }

    public void transferFirst(Processor recipient) {
        Process process = heldProcesses.get(0);
        heldProcesses.remove(0);
        recipient.insert(process);
    }

    public void transferLast(Processor recipient) {
        int index = heldProcesses.size() - 1;
        Process process = heldProcesses.get(index);
        heldProcesses.remove(index);
        recipient.insert(process);
    }

    @Override
    public String toString() {
        return getCurrentLoad() + "@" + getId();
    }
}
