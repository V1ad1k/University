import java.util.ArrayList;

public class Selector {
    private int lastROTIndex = -1;

    public int fcfs(ArrayList<Process> processes) {
        return 0; // By design
    }

    public int sjf(ArrayList<Process> processes) {
        int current = 0;
        for (int i = 0; i < processes.size(); i++) {
            if (processes.get(i).time < processes.get(current).time) {
                current = i;
            }
        }
        return current;
    }

    public int sjf_pre(ArrayList<Process> processes) {
        int current = 0;
        for (int i = 0; i < processes.size(); i++) {
            if (processes.get(i).timeLeft < processes.get(current).timeLeft) {
                current = i;
            }
        }
        return current;
    }

    public int rot(ArrayList<Process> processes) {
        int lastIndex = processes.size() - 1;
        lastROTIndex++;
        if (lastROTIndex > lastIndex) {
            lastROTIndex = 0;
        }
        return lastROTIndex;
    }
}
