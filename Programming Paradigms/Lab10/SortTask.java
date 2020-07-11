import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

class SortTask extends RecursiveAction {
    int[] dataToProcess;
    final int threshold = 500/Runtime.getRuntime().availableProcessors();
    int start, end;

    SortTask(int[] dataToProcess, int start, int finish) {
        this.dataToProcess = dataToProcess;
        this.start = start;
        this.end = finish;
    }
    @Override
    protected void compute() { 
        if (end - start < threshold) 
        {
            Arrays.sort(dataToProcess);
        } else { 
            int middle = (start + end) / 2;
            invokeAll(new SortTask(dataToProcess, start, middle), new SortTask(dataToProcess, middle, end));
        }
    }
}