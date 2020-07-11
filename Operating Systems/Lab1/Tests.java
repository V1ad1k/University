import java.util.ArrayList;

public class Tests {
    public static void main(String[] args) {
        //TODO: Proper tests
        ProcessFactory processFactory = new ProcessFactory();
        ArrayList<Process> processes = processFactory.getRandomArray(20, 1, 2);
        Queue queue = new Queue(processes);
        queue.simulate(SortingMode.SJF);
        System.out.println(queue);
    }
}
