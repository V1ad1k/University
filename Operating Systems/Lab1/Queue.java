import java.util.ArrayList;

public class Queue {
    public ArrayList<Process> toSort;
    public ArrayList<Process> sorted = new ArrayList<>();
    public boolean showMessage = true;
    public double totalTime = 0.0;
    public double timeUnit = 1.0;

    public Queue(ArrayList<Process> toSort) {
        this.toSort = toSort;
    }

    public void setTimeUnit(double timeUnit) {
        this.timeUnit = timeUnit;
    }

    public void setToSort(ArrayList<Process> toSort) {
        this.toSort = toSort;
    }

    public void insertNewProcess(Process process) {
        toSort.add(process);
    }

    public boolean removeTerminated() {
        boolean flag = false;
        for (int i = 0; i < toSort.size(); i++) {
            if (toSort.get(i).status == Status.TERMINATED) {
                flag = true;
                toSort.remove(i);
                i -= 1;
            }
        }
        return flag;
    }

    public void simulate(SortingMode mode, int breakID, double time) {
        //Behaves differently depending on the number of parameters filled in:
        //mode - sorts normally
        //mode, breakID - sorts normally, stops simulation after encountering a process with given ID
        //mode, breakID, time - sorts normally, decreases process' time if its ID matches breakID
        Selector selector = new Selector();
        if (showMessage) {
            System.out.println("Simulation starts with " + toSort.size() + " processes.");
            if (removeTerminated()) {
                System.out.println("Removed terminated processes - " + toSort.size() + " left.");
            }
            showMessage = false;
        }
        if (removeTerminated()) {
            System.out.println("Found leftover terminated processes.");
        }
        while (toSort.size() > 0) {
            int index = 0;
            switch (mode) {
                case FCSF:
                    index = selector.fcfs(toSort);
                    break;
                case SJF:
                    index = selector.sjf(toSort);
                    break;
                case SJF_PRE:
                    index = selector.sjf_pre(toSort);
                    break;
                case ROT:
                    index = selector.rot(toSort);
                    break;
            }
            if (mode == SortingMode.ROT) {
                boolean toBreak = false;
                double leftover = toSort.get(index).timeLeft;
                if (leftover < timeUnit) {
                    totalTime += leftover;
                } else {
                    totalTime += timeUnit;
                }
                toSort.get(index).decreaseTimeLeft(timeUnit);
                if (breakID == toSort.get(index).id) {
                    toBreak = true;
                }
                if (toSort.get(index).status == Status.TERMINATED) {
                    sorted.add(toSort.get(index));
                    removeTerminated();
                }
                if (toBreak) {
                    break;
                }

            } else {
                if (breakID == toSort.get(index).id && time > 0.0) {
                    toSort.get(index).decreaseTimeLeft(time);
                    totalTime += time;
                    break;
                }
                totalTime += toSort.get(index).time;
                toSort.get(index).decreaseTimeLeft(toSort.get(index).time); // Sanity check
                toSort.get(index).setStatus(Status.TERMINATED); // Sanity check
                sorted.add(toSort.get(index));
                if (breakID == toSort.get(index).id && time < 0.0) {
                    break;
                }
                removeTerminated();
            }
        }
    }

    public void simulate(SortingMode mode, int breakIndex) {
        simulate(mode, breakIndex, -1.0);
    }

    public void simulate(SortingMode mode) {
        simulate(mode, -1, -1.0);
    }

    @Override
    public String toString() {
        //TODO: Average waiting time
    	double average=0;
    	double total = 0;
        for(int i=1;i<sorted.size();i++)
        {
        	for (int j=0;j<i;j++)
        		 total += sorted.get(j).time;
        }
        average = total/sorted.size();
        System.out.println(average);
        if (sorted.size() == 0) {
            return "The queue isn't sorted.";
        }
        if (toSort.size() > 0 && sorted.size() > 0) {
            return "The queue isn't fully sorted.";
        }
        StringBuilder outputBuilder = new StringBuilder("[");
        for (Process process : sorted) {
            outputBuilder.append(process.id).append(", ");
        }
        outputBuilder.setLength(outputBuilder.length() - 2);
        String output = outputBuilder.toString();
        output += "]";
        return output;
    }
}
