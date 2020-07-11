package Task2;

import java.util.ArrayList;

public class Selector {
    public int fcfs(ArrayList<Integer> processes, Disk disk) {
        return 0; // By design
    }

    public int fcfs() {
        return 0; // By design
    }

    public int sstf(ArrayList<Integer> processes, Disk disk) {
        int shortestRoute = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < processes.size(); i++) {
            if (disk.posDiff(processes.get(i)) < shortestRoute) {
                shortestRoute = disk.posDiff(processes.get(i));
                index = i;
            }
        }
        return index;
    }

    public int scan(ArrayList<Integer> processes, Disk disk) {
        int index = 0;
        if (processes.size() == 1) {
            return index;
        }
        int closest = Integer.MAX_VALUE;
        boolean flag = false;
        if (disk.doesGoRight()) {
            for (int i = 0; i < processes.size(); i++) {
                if (disk.posDiff(processes.get(i)) < closest && processes.get(i) > disk.getHeadPos()) {
                    closest = disk.posDiff(processes.get(i));
                    index = i;
                    flag = true;
                }
            }
            if (!flag) {
                disk.setGoesRight(false);
                return scan(processes, disk);
            }
        } else {
            for (int i = 0; i < processes.size(); i++) {
                if (disk.posDiff(processes.get(i)) < closest && processes.get(i) < disk.getHeadPos()) {
                    closest = disk.posDiff(processes.get(i));
                    index = i;
                    flag = true;
                }
            }
            if (!flag) {
                disk.setGoesRight(true);
                return scan(processes, disk);
            }
        }
        return index;
    }

    public int cscan(ArrayList<Integer> processes, Disk disk) {
        int index = 0;
        if (processes.size() == 1) {
            return index;
        }
        int closest = Integer.MAX_VALUE;
        boolean found = false;
        for (int i = 0; i < processes.size(); i++) {
            if (disk.posDiff(processes.get(i)) < closest && processes.get(i) > disk.getHeadPos()) {
                found = true;
                index = i;
                closest = disk.posDiff(processes.get(i));
            }
        }
        if (found) {
            return index;
        } else {
            int smallest = Integer.MAX_VALUE;
            for (int i = 0; i < processes.size(); i++) {
                if (processes.get(i) < smallest) {
                    smallest = processes.get(i);
                    index = i;
                }
            }
        }
        return index;
    }

    public int edf(ArrayList<Integer> deadlines) {
        int earliest = Integer.MAX_VALUE;
        int earliestIndex = Integer.MAX_VALUE;
        for (int i = 0; i < deadlines.size(); i++) {
            if (deadlines.get(i) < earliest) {
                earliest = deadlines.get(i);
                earliestIndex = i;
            }
        }
        return earliestIndex;
    }

    public int fdscan(ArrayList<Integer> processes, Disk disk, ArrayList<Integer> deadlines) {
        int earliest = edf(deadlines);
        int index = 0;
        int distance = Integer.MAX_VALUE;
        boolean found = false;
        for (int i = 0; i < processes.size(); i++) {
            boolean rightHand = disk.getHeadPos() < processes.get(earliest) && processes.get(i) < processes.get(earliest);
            boolean leftHand = disk.getHeadPos() > processes.get(earliest) && processes.get(i) > processes.get(earliest);
            if (disk.posDiff(processes.get(i)) < distance && (rightHand || leftHand)) {
                found = true;
                index = i;
                distance = disk.posDiff(processes.get(i));
            }
        }
        if (found) {
            return index;
        }
        return earliest;
    }
}
