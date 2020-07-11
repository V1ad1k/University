import java.util.Comparator;

public class TryBComparator implements Comparator<Task> {
    public int compare(Task task1, Task task2) {
        return task2.getTryb().compareTo(task1.getTryb());
    }
}
